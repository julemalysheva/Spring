
package com.example.authorizationserver;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * Конфигурация сервера авторизации OAuth2.
 */
@Configuration
@EnableWebSecurity
public class OAuth2AuthorizationServerSecurityConfiguration {

	/**
	 * Настройка цепочки фильтров для безопасности сервера авторизации.
	 *
	 * @param http {@link HttpSecurity}
	 * @return Цепочка фильтров безопасности сервера авторизации
	 * @throws Exception Исключение в случае ошибки конфигурации
	 */
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
		http.getConfigurer(OAuth2AuthorizationServerConfigurer.class).oidc(Customizer.withDefaults()); // Enable OpenID Connect 1.0
		http
				.exceptionHandling((exceptions) -> exceptions
						.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
				)
				.oauth2ResourceServer((resourceServer) -> resourceServer.jwt(Customizer.withDefaults()));
		return http.build();
	}

	/**
	 * Настройка цепочки фильтров для стандартной безопасности.
	 *
	 * @param http {@link HttpSecurity}
	 * @return Цепочка фильтров стандартной безопасности
	 * @throws Exception Исключение в случае ошибки конфигурации
	 */
	@Bean
	@Order(2)
	public SecurityFilterChain standardSecurityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((authorize) -> authorize
				.anyRequest().authenticated()
			)
			.formLogin(Customizer.withDefaults());

		return http.build();
	}

	/**
	 * Репозиторий зарегистрированных клиентов.
	 *
	 * @return Репозиторий зарегистрированных клиентов
	 */
	@Bean
	public RegisteredClientRepository registeredClientRepository() {
		RegisteredClient loginClient = RegisteredClient.withId(UUID.randomUUID().toString())
				.clientId("login-client")
				.clientSecret("{noop}openid-connect")
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
				.redirectUri("http://127.0.0.1:8090/login/oauth2/code/login-client")
				.redirectUri("http://127.0.0.1:8090/authorized")
				.scope(OidcScopes.OPENID)
				.scope(OidcScopes.PROFILE)
				.clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
				.build();
		RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
				.clientId("messaging-client")
				.clientSecret("{noop}secret")
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
				.scope("message:read")
				.scope("message:write")
				.build();

		return new InMemoryRegisteredClientRepository(loginClient, registeredClient);
	}

	/**
	 * Источник JWK (JSON Web Key) для сервера авторизации.
	 *
	 * @param keyPair Ключевая пара
	 * @return Источник JWK
	 */
	@Bean
	public JWKSource<SecurityContext> jwkSource(KeyPair keyPair) {
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		RSAKey rsaKey = new RSAKey.Builder(publicKey)
				.privateKey(privateKey)
				.keyID(UUID.randomUUID().toString())
				.build();
		JWKSet jwkSet = new JWKSet(rsaKey);
		return new ImmutableJWKSet<>(jwkSet);
	}

	/**
	 * Декодер JWT (JSON Web Token) для сервера авторизации.
	 *
	 * @param keyPair Ключевая пара
	 * @return Декодер JWT
	 */
	@Bean
	public JwtDecoder jwtDecoder(KeyPair keyPair) {
		return NimbusJwtDecoder.withPublicKey((RSAPublicKey) keyPair.getPublic()).build();
	}

	/**
	 * Настройки провайдера авторизации.
	 *
	 * @return Настройки провайдера авторизации
	 */
	@Bean
	public AuthorizationServerSettings providerSettings() {
		return AuthorizationServerSettings.builder().issuer("http://localhost:9000").build();
	}

	/**
	 * Сервис пользовательских данных.
	 *
	 * @return Сервис пользовательских данных
	 */
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails userDetails = User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(userDetails);
	}

	/**
	 * Генерация RSA ключевой пары.
	 *
	 * @return RSA ключевая пара
	 */
	@Bean
	@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
	KeyPair generateRsaKey() {
		KeyPair keyPair;
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048);
			keyPair = keyPairGenerator.generateKeyPair();
		}
		catch (Exception ex) {
			throw new IllegalStateException(ex);
		}
		return keyPair;
	}

}
