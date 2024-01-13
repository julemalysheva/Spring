package ru.geekbrains;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.config.ProjectConfig;
import ru.geekbrains.service.CommentService;

public class Main {
    public static void main(String[] args) {
        var c = new AnnotationConfigApplicationContext(ProjectConfig.class);

        var cs1 = c.getBean("commentService", CommentService.class);
        var cs2 = c.getBean("commentService", CommentService.class);
        // System.out.println("Hello world!");

        boolean b1 = cs1 == cs2;
        System.out.println(b1);
        System.out.println(cs1);
        System.out.println(cs2);

        /**
         * Создан CommentService!
         * Создан CommentService!
         * false
         * ru.geekbrains.service.CommentService@6b2ea799
         * ru.geekbrains.service.CommentService@411f53a0
         */
    }
}