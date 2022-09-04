package com.example.templates.beans;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.RequestScope;

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

    @Bean
    public String strSingleton() {
        return ""+ Math.random() * 10;
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public String strProtoType() {
        return "" + Math.random() * 10;
    }

    @Bean
    @RequestScope
    public List<String> strRequest() {
        List<String> x = new ArrayList<>();
        x.add("" + Math.random() * 10);
        return x;
    }
}
