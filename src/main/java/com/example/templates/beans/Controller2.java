package com.example.templates.beans;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bean2")
public class Controller2 {

    private String strSingleton;
    private String strProtoType;
    @Resource(name = "strRequest")
    private List<String> strRequest;

    public Controller2(String strSingleton, String strProtoType) {
        this.strSingleton = strSingleton;
        this.strProtoType = strProtoType;

    }

    @RequestMapping("1")
    public String get1() {
        return this.strSingleton;
    }

    @RequestMapping("2")
    public String get2() {
        return this.strProtoType;
    }

    @RequestMapping("3")
    public List<String> get3() {
        return this.strRequest;
    }
}
