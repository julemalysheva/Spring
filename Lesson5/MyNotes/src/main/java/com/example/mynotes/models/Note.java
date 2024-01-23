package com.example.mynotes.models;


import jakarta.persistence.*;

/**
 * В этом классе мы видим несколько важных аннотаций:
 * ● @Entity: эта аннотация указывает, что класс является JPA сущностью.
 * ● @Table: эта аннотация позволяет нам указать имя таблицы, на которую будет
 * отображаться наш класс.
 * ● @Id и @GeneratedValue: эти аннотации указывают, что поле id является
 * идентификатором и его значение должно быть сгенерировано
 * автоматически.
 * ● @Column: эта аннотация позволяет нам указать параметры для колонки,
 * соответствующей данному полю. В данном случае, мы указываем, что все
 * поля являются обязательными (nullable = false), а поле content ограничиваем
 * 2000 символами в длину
 */
@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, length = 2000)
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Note() {

    }
}

