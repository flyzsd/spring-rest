package com.shudong.spring.springrest.service;

import com.shudong.spring.springrest.repository.UserEntity;
import com.shudong.spring.springrest.service.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.Optional;

import static info.solidsoft.mockito.java8.AssertionMatcher.assertArg;
import static info.solidsoft.mockito.java8.LambdaMatcher.argLambda;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@Slf4j
class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserEntity.Repo userRepo;

    @BeforeAll
    static void setupAll() {
        log.info("setupAll");
    }

    @BeforeEach
    void setup() {
        log.info("setup");
        reset(userRepo);
    }

    @Test
    void shouldInjectMocks() {
        assertThat(userService).isNotNull();
        assertThat(userRepo).isNotNull();
    }

    @Nested
    class FindUserTest {

        //BDD style - given, when, then
        //given some preconditions (Arrange)
        //when an action occurs (Act)
        //then verify the output (Assert)
        @Test
        void shouldReturnEmptyOptionalIfUserNotFound() {
            //given
            String id = "xyz-abc";
            //Lambda matcher
            given(userRepo.findById(argLambda(e -> Objects.equals(id, "xyz-abc")))).willReturn(Optional.empty());
            //when
            Optional<User> optionalUser = userService.getUser(id);
            //then
            assertThat(optionalUser).isNotNull();
            assertThat(optionalUser.isPresent()).isFalse();
            //Argument Captor
            verify(userRepo, times(1)).findById(assertArg(e -> assertThat(e).isEqualTo(id)));
        }

        @Test
        void shouldThrowException() {
            String id = "xyz-abc";
            given(userRepo.findById(anyString())).willThrow(RuntimeException.class);
            assertThatThrownBy(() -> userService.getUser(id)).isInstanceOf(RuntimeException.class);
        }
    }

}
