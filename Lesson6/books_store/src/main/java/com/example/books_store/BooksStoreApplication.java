package com.example.books_store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//@EnableSwagger2
public class BooksStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BooksStoreApplication.class, args);
    }

}
