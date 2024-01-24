package com.example.sem5_springdata_ex2.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();
}

/**
 * Параметр mappedBy = "courses":
 *  *    Указывает, что поле courses в классе Course является владельцем связи. Это означает, что Course является
 *  *    владеющей стороной связи, а Student использует эту информацию для указания, какое поле в классе Course устанавливает
 *  *    отношение между студентами и курсами.
 */