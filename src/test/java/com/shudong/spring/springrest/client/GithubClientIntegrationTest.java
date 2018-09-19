package com.shudong.spring.springrest.client;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class GithubClientIntegrationTest {
    @Autowired
    GithubClient githubClient;

    @Test
    @DisplayName("github contributors")
    void testGetContributors() {
        List<GithubClient.Contributor> contributors = githubClient.contributors("flyzsd", "spring-rest", "xyz-token", "123456789");
        System.out.println(contributors);
    }
}
