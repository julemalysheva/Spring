package com.example.books_store;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@Configuration
//@EnableSwagger2
////@EnableWebMvc
//public class SwaggerConfig {
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.example.books_store"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("My API")
//                .description("API documentation for my project")
//                .version("1.0")
//                .contact(new Contact("Yuliya Malysheva", "", "jule.mal@example.com"))
//                .build();
//    }
//}

//@EnableSwagger2
//@Configuration
//public class SwaggerConfig {
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.example.books_store"))
//                .build();
//    }
//}

/** еще вариант конфига
 * @Configuration
 * public class SpringFoxConfig {
 *     @Bean
 *     public Docket api() {
 *         return new Docket(DocumentationType.SWAGGER_2)
 *           .select()
 *           .apis(RequestHandlerSelectors.any())
 *           .paths(PathSelectors.any())
 *           .build();
 *     }
 * }
 */
