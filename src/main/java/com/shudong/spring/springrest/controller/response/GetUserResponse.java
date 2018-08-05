package com.shudong.spring.springrest.controller.response;

import com.shudong.spring.springrest.service.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
public class GetUserResponse {
    private String id;
    private String name;
    private String email;
    private Instant modifiedOn;
    private String data;
    private long version;

    public GetUserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.modifiedOn = user.getModifiedOn();
        this.data = user.getData();
        this.version = user.getVersion();
    }
}
