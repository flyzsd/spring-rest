package com.shudong.spring.springrest.service;

import com.shudong.spring.springrest.repository.UserEntity;
import com.shudong.spring.springrest.service.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("test")
public class UserServiceIntegrationTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserEntity.Repo userRepo;


    @Nested
    class UserFound {
        @BeforeEach
        void setup() {
        }

        @Test
        void testGetUser() {
            UserEntity userEntity = UserEntity.builder()
                    .name("shudong")
                    .email("shudong@mail.com")
                    .data("{}")
                    .modifiedOn(Timestamp.from(Instant.now()))
                    .build();
            UserEntity dbUserEntity = userRepo.saveAndFlush(userEntity);

            Optional<User> optionalUser = userService.getUser(dbUserEntity.getId());
            assertThat(optionalUser.isPresent()).isTrue();
        }
    }
}
