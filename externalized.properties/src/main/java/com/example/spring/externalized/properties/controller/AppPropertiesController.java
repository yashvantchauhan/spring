package com.example.spring.externalized.properties.controller;

import com.example.spring.externalized.properties.config.TenantConfig;
import com.example.spring.externalized.properties.model.ConfigResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/properties")
@Slf4j
public class AppPropertiesController {

    @Value("${datasource.username}")
    String username;

    @Value("${datasource.password}")
    String password;

    @Value("${datasource.service}")
    String service;

    @Autowired
    TenantConfig tenantConfig;

    @GetMapping(value = "{key}", produces = MediaType.APPLICATION_JSON_VALUE)

    public Mono<ConfigResponse> getPropertiesValue(@PathVariable String key){

        Map config= new HashMap();
        config.put(key, tenantConfig.getTenant().get(key));
        log.info("Received key: {} , Value: {} ",key, tenantConfig.getTenant().get(key));

        ConfigResponse response=ConfigResponse.builder()
                .config(config)
                .databaseService(service)
                .databasePassword(password).databaseUsername(username).build();
        log.info("Response: {} ",response);
        return Mono.just(response);
    }
}
