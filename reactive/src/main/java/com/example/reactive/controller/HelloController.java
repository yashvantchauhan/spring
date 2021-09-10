package com.example.reactive.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reactive")
@Slf4j
public class HelloController {

    @Value("${example.datasource.url}")
    String url;

    @Value("${example.datasource.username}")
    String username;
    @Value("${example.datasource.password}")
    String password;

    @GetMapping
    public String hello(){

        log.info("URL : {}, username: {}, password : {}", url, username, password );
        return "Hello there!";
    }
}
