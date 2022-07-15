package com.example.templates.beans;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Services {
    @Bean
    public String title() {
        return "java-templates";
    }

    @Bean(name = {"alias1", "alias2"})
    public String aliases() {
        return "Alias";
    }

    @Bean
    public Properties appProperties() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        Properties properties = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream("application.properties")) {
            properties.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return properties;
    }
}
