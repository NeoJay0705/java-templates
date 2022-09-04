package com.example.templates.dao;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class DaoController {
    private UserEntityService userEntityService;

    public DaoController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
        
    }

    @RequestMapping("all")
    public List<UserEntity> getAll() {
        return userEntityService.getAll();
    }

    @RequestMapping("all2")
    public List<UserEntity> getAll2() {
        return userEntityService.getAll2();
    }

    @RequestMapping("insert")
    public List<UserEntity> insert() {
        this.userEntityService.staticInsert();
        return this.userEntityService.getAll();
    }

    @RequestMapping("batchInsert")
    public List<UserEntity> batchInsert() {
        this.userEntityService.batchStaticInsert();
        return this.userEntityService.getAll();
    }

    @RequestMapping("sp")
    public List<UserEntity> call() {
        return this.userEntityService.callSP();
    }
}
