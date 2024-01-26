package com.example.usersprojectrelation.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * Класс сущности "Проект"
 */

@Entity
@Table(name = "projects")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(name = "created_date") //, nullable = false
    @Temporal(TemporalType.DATE)
    @JsonProperty("createdDate")
    private LocalDate createdDate;


    @OneToMany(mappedBy = "project") // Один проект имеет много связей UsersProject
    @JsonIgnore
    private List<UsersProject> usersProjects; // Список связей UsersProject


}
