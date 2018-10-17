package com.example.echo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
@ConfigurationProperties(prefix = "jks")
@NoArgsConstructor
@Data
public class JKSConfiguration {

    private Resource keystore;
}
