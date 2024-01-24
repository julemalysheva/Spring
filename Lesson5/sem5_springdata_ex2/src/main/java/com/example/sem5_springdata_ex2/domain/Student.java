package com.example.sem5_springdata_ex2.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Struct;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses = new HashSet<>();


//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "student_courses",
//            joinColumns = @JoinColumn(name = "student_id"),
//            inverseJoinColumns = @JoinColumn(name = "course_id"))
//    private Set<Course> courses = new HashSet<>();
}

/**
 * В классах Student и Course установлена отношение многие-ко-многим с использованием аннотации @ManyToMany.
 * Это означает, что каждый студент может быть записан на несколько курсов, и каждый курс может иметь нескольких студентов.
 * Для установления этой связи между двумя классами используются таблицы-соединения (join tables).
 *
 * Вот объяснение параметров аннотации @ManyToMany и что происходит между классами Student и Course:
 *
 * 1. Параметр fetch = FetchType.LAZY:
 *    Указывает, что данные о записях курсов Student должны быть получены лениво, только при первом доступе.
 *    То есть данные о курсах будут получены только при обращении к этому полю.
 *
 * 2. Параметр cascade = CascadeType.ALL:
 *    Определяет, что все операции каскадной обработки (создание, обновление, удаление) на объекте Student
 *    также применятся к связанным с ним объектам Course. То есть при добавлении, изменении или удалении студента
 *    автоматически будут внесены соответствующие изменения в связанные курсы.
 *
 * 3. Параметр mappedBy = "courses":
 *    Указывает, что поле courses в классе Course является владельцем связи. Это означает, что Course является
 *    владеющей стороной связи, а Student использует эту информацию для указания, какое поле в классе Course устанавливает
 *    отношение между студентами и курсами.
 *
 * 4. Аннотация @JoinTable:
 *    Используется для настройки дополнительной таблицы-соединения между студентами и курсами. В данном случае
 *    аблица-соединение называется "student_courses" и имеет два столбца: "student_id" и "course_id". Через эту
 *    таблицу связываются студенты с соответствующими курсами.
 *
 * Таким образом, между классами Student и Course создано многие-ко-многим отношение с использованием таблицы-соединения
 * "student_courses". Каждый студент может быть записан на несколько курсов, и каждый курс может иметь нескольких студентов.
 * Аннотации @ManyToMany, @JoinTable и указанные параметры позволяют установить и настроить это отношение в Spring Data JPA.
 */

/**
 * то при использовании fetch = FetchType.LAZY связанные данные не будут загружены автоматически.
 * Вместо этого, они будут загружены только при обращении к ним.
 *
 * Если вы хотите загрузить все связанные данные при запросе, то можно изменить FetchType на EAGER:
 *
 * @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL, CascadeType.REMOVE})
 * @JoinTable(name = "student_courses",
 *         joinColumns = @JoinColumn(name = "student_id"),
 *         inverseJoinColumns = @JoinColumn(name = "course_id"))
 * private Set<Course> courses = new HashSet<>();
 *
 * Таким образом, при запросах к студентам вы также увидите все связанные данные по курсам, и наоборот.
 *
 * Однако, следует учитывать, что использование FetchType.EAGER может привести к дополнительной нагрузке на
 * базу данных и снижению производительности при обработке большого количества данных. Поэтому, в некоторых случаях,
 * может быть рациональнее использовать явное загрузку связанных данных при необходимости, например,
 * с помощью применения метода fetch = FetchType.LAZY и выполнения дополнительных запросов в коде.
 */