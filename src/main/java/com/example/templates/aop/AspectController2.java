package com.example.templates.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("aspect2")
public class AspectController2 {

    @RequestMapping("getName/{x}")
    public String getName(@PathVariable String x) {
        try {
            testObject();
            return "neo";
        } finally {
            System.out.println("finally");
        }
    }

    @AspectAnnotation
    @Bean
    public String testObject() {
        return "123";
    }
}
