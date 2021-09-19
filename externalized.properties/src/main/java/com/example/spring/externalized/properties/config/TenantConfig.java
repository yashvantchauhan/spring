package com.example.spring.externalized.properties.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties
@Data
public class TenantConfig {

    Map<String, String> tenant;

}
