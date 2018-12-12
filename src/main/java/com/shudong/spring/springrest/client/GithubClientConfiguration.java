package com.shudong.spring.springrest.client;

import feign.RequestInterceptor;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;

public class GithubClientConfiguration extends FeignClientsConfiguration {

    @Bean
    public RequestInterceptor basicAuthRequestInterceptor() {
        return new OAuth2RequestInterceptor();
    }

}
