package com.shudong.spring.springrest.controller;

import com.shudong.spring.springrest.controller.response.GetUserResponse;
import com.shudong.spring.springrest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/{id}")
    public GetUserResponse getUser(@PathVariable String id) {
        return userService.getUser(id).map(GetUserResponse::new).orElseGet(GetUserResponse::new);
    }
}
