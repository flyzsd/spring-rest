package com.shudong.spring.springrest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping(path = "/health")
    public Properties health() {
        return System.getProperties();
    }

}
