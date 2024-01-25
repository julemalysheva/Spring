package com.example.usersprojectrelation.domain;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Абстрактный класс "Сущность со связью"
 * */
@MappedSuperclass
@Data
public abstract class EntityWithRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    /**
     * Идентификатор связанной сущности
     * непонятно - как применим в UsersProject, если там уже будет два поля для связей?
     */
    @Column(name = "related_entity_id")  //@Column(nullable = false)
    protected Long relatedEntityId;


}
