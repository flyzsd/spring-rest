package com.shudong.spring.springrest.service;

import com.shudong.spring.springrest.client.GithubClient;
import com.shudong.spring.springrest.repository.UserEntity;
import com.shudong.spring.springrest.service.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserEntity.Repo userRepo;

    @Autowired
    private GithubClient githubClient;

    @PostConstruct
    public void init(){
        log.info("init");
    }

    @Transactional(readOnly = true)
    public Optional<User> getUser(String id) {
        Optional<User> optionalUser = userRepo.findById(id).map(User::new);
        return optionalUser;
    }

    public List<GithubClient.Contributor> getContributors() {
        List<GithubClient.Contributor> contributors = githubClient.contributors("flyzsd", "spring-rest", "xxx", "123456789");
        return contributors;
    }
}
