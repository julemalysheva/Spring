package ru.geekbrains.config;


import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.geekbrains.service.CommentService;

@Configuration
public class ProjectConfig {

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public CommentService commentService() {
        return new CommentService();
    }

    /**
     * Аннотация @Scope используется в Spring для определения области видимости (scope) бина,
     * то есть для указания, как долго будет существовать экземпляр бина и как будет управляться
     * его состояние.
     *
     * При использовании аннотации @Scope с параметром BeanDefinition.SCOPE_PROTOTYPE указывается,
     * что каждый запрос на получение бина будет создавать новый экземпляр, т.е. для каждого запроса
     * будет создаваться новый объект бина. Это отличается от области видимости по умолчанию,
     * Singleton (BeanDefinition.SCOPE_SINGLETON), при которой создается только один экземпляр
     * бина, и все запросы на получение бина возвращают ссылку на этот один и тот же экземпляр.
     */

}