package com.shudong.spring.springrest.controller;

import com.shudong.spring.springrest.controller.request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class LoginController {
    private final RestTemplateBuilder restTemplateBuilder;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        headers.add(AUTHORIZATION, "Basic b2F1dGgyLWNsaWVudC1pZDpvYXV0aDItY2xpZW50LXNlY3JldA==");
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("username", request.getUsername());
        body.add("password", request.getPassword());
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, headers);
        return this.restTemplateBuilder.build().postForObject("http://localhost:8081/oauth/token", httpEntity, String.class);
    }
}
