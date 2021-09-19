package com.example.spring.externalized.properties.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Map;

@Data
@Builder
@ToString
public class ConfigResponse {
    String databaseUsername;
    String databasePassword;
    String databaseService;
    Map<String, String> config;
}
