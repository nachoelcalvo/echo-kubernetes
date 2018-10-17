package com.example.echo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "container")
@NoArgsConstructor
@Data
public class EchoConfig {

    private String username;

    private String password;

    private String owner;
}