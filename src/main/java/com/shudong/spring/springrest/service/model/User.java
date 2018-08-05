package com.shudong.spring.springrest.service.model;

import com.shudong.spring.springrest.repository.UserEntity;
import lombok.Getter;

import java.time.Instant;

@Getter
public class User {
    private String id;
    private String name;
    private String email;
    private Instant modifiedOn;
    private String data;
    private long version;

    public User(UserEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.modifiedOn = entity.getModifiedOn().toInstant();
        this.data = entity.getData();
        this.version = entity.getVersion();
    }
}
