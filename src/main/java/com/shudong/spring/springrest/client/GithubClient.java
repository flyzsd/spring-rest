package com.shudong.spring.springrest.client;

import lombok.Getter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(name = "github", url = "${feign.github.url}", configuration = GithubClientConfiguration.class)
public interface GithubClient {

    @Getter
    class Contributor {
        String login;
        int contributions;
    }

    @RequestMapping(method = GET, value = "/repos/{owner}/{repo}/contributors", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<Contributor> contributors(@PathVariable("owner") String owner, @PathVariable("repo") String repo, @RequestHeader("X-Auth-Token") String token, @RequestParam(name = "queryId", required = false, defaultValue = "abc") String queryId);
}
