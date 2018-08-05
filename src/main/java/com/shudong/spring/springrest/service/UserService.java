package com.shudong.spring.springrest.service;

import com.shudong.spring.springrest.repository.UserEntity;
import com.shudong.spring.springrest.service.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserEntity.Repo userRepo;

    @Transactional(readOnly = true)
    public Optional<User> getUser(String id) {
        Optional<User> optionalUser = userRepo.findById(id).map(User::new);
        return optionalUser;
    }
}