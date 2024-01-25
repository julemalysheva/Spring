package com.example.usersprojectrelation.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Класс сущности "Пользователи проекта"
 */

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users_projects")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersProject extends EntityWithRelation {

    /**
     * Идентификатор проекта, к которому привязан пользователь.
     */
    @Column(nullable = false, name = "project_id")
    private Long projectId;

    /**
     * Идентификатор пользователя, привязанного к проекту.
     */
    @Column(nullable = false, name = "user_id")
    private Long userId;

    @ManyToOne // Множество UsersProject относятся к одному проекту
    @JoinColumn(name = "project_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Project project; // Ссылка на проект

    @ManyToOne // Множество UsersProject относятся к одному пользователю
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user; // Ссылка на пользователя


}
