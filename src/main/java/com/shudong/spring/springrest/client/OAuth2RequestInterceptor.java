package com.shudong.spring.springrest.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class OAuth2RequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorization", "ABC");
    }

}
