package com.shudong.spring.springrest.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class UserEntityTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserEntity.Repo userRepo;

    @Test
    @DisplayName("find user by id")
    void testFindUserById() {
        Optional<UserEntity> optionalUserEntity = userRepo.findById("xyz");
        assertThat(optionalUserEntity).isNotNull();
        assertThat(optionalUserEntity.isPresent()).isFalse();

        UserEntity userEntity = UserEntity.builder()
                .name("shudong")
                .email("shudong@mail.com")
                .data("{}")
                .modifiedOn(Timestamp.from(Instant.now()))
                .build();
        UserEntity dbUserEntity = entityManager.persistAndFlush(userEntity);

        optionalUserEntity = userRepo.findById(dbUserEntity.getId());
        assertThat(optionalUserEntity).isNotNull();
        assertThat(optionalUserEntity.get().getId()).isNotBlank();
        assertThat(optionalUserEntity.get().getName()).isEqualTo("shudong");
    }
}
