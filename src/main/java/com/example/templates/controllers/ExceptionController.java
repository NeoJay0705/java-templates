package com.example.templates.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("exception")
public class ExceptionController {
    @RequestMapping("/")
    public int exception() throws Exception {
        if (1 == 1)
            throw new Exception();
        return 0;
    }

    @RequestMapping("/customized")
    public int customizedException() {
        if (1 == 1)
            throw new CustomizedException();
        return 0;
    }

    @RequestMapping("/customized2")
    public int customized2Exception() {
        if (1 == 1)
            throw new CustomizedException2();
        return 0;
    }
}
