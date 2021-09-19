package com.example.spring.externalized.properties.controller;

import com.example.spring.externalized.properties.config.TenantConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/properties")
@Slf4j
public class AppPropertiesController {


    @Autowired
    TenantConfig tenantConfig;

    @GetMapping("{key}")
    public Mono<String> getPropertiesValue(@PathVariable String key){
        log.info("Received key: {} , Value: {} ",key, tenantConfig.getTenant().get(key));
        return Mono.just(String.format("Key: %s, value: %s", key, tenantConfig.getTenant().get(key)));
    }
}
