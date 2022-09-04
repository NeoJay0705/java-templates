package com.example.templates.aop;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("aspect")
public class AspectController {
    @RequestMapping("getName/{x}")
    public String getName(@PathVariable String x) {
        try {
            testObject(null);
            return "neo";
        } finally {
            System.out.println("finally");
        }
    }

    
    @AspectAnnotation
    @RequestMapping("getName4/{x}")
    public String testObject(TestObject x) {
        return "123";
    }

    @RequestMapping("getName2")
    public String getName2() {
        if (1==1)
            throw new RuntimeException();
        return "neo";
    }

    @AspectAnnotation
    @RequestMapping("getName3")
    public String getName3() {
        return "neo";
    }
}
