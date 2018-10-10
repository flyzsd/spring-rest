package com.shudong.spring.springrest;

import com.shudong.spring.springrest.repository.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.Arrays;

@Slf4j
@EnableJpaRepositories(considerNestedRepositories = true)
@EnableFeignClients
@SpringBootApplication
public class SpringRestApplication extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(UserEntity.Repo repo) {
        return args -> {
            log.info("CommandLineRunner running in the SpringRestApplication class...");
            log.info("args = {}", Arrays.toString(args));
            log.info("repo = {}", repo.toString());
        };
    }

}
