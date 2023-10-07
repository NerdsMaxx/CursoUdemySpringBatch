package com.udemy.aulaspringbatch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class PropsConfig {

    @Bean
    public PropertySourcesPlaceholderConfigurer config() {
        final PropertySourcesPlaceholderConfigurer configurer;
        configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(new FileSystemResource("/etc/config/aulaspringbatch/application.properties"));
        return configurer;
    }
}