package com.example.templates.jpa;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.templates.jpa.model.UserEntity;

@RestController
@RequestMapping("user")
public class JpaController {
    private JpaUserEntityService userEntityService;

    public JpaController(JpaUserEntityService userEntityService) {
        this.userEntityService = userEntityService;
        
    }

    @RequestMapping("jpa/all")
    public List<UserEntity> getAll() {
        return userEntityService.getAllUsers();
    }

    @RequestMapping("jpa/{name}")
    public List<UserEntity> getbyName(@PathVariable String name) {
        return userEntityService.getbyName(name);
    }

    @RequestMapping("jpa/{name}/and/{age}")
    public List<UserEntity> getbyNameAndAge(@PathVariable String name, @PathVariable int age) {
        return userEntityService.getbyNameAndAge(name, age);
    }

    @RequestMapping("jpa/{name}/or/{age}")
    public List<UserEntity> getbyNameOrAge(@PathVariable String name, @PathVariable int age) {
        return userEntityService.getbyNameOrAge(name, age);
    }

    @RequestMapping("jpa/save/{name}/{age}")
    public List<UserEntity> savebyNameOrAge(@PathVariable String name, @PathVariable int age) {
        return userEntityService.savebyNameOrAge(new UserEntity(name, age));
    }

    @RequestMapping("jpa/sort/{name}")
    public List<UserEntity> getbyNameSorted(@PathVariable String name) {
        return userEntityService.getbyNameSorted(name);
    }

    @RequestMapping("jpa/slice/{name}/{page}")
    public List<UserEntity> getbyNameSlice(@PathVariable String name, @PathVariable int page) {
        return userEntityService.getbyNameSlice(name, page);
    }

    @RequestMapping("jpa/native/query/all")
    public List<UserEntity> getAllByNative() {
        return userEntityService.getAllByNative();
    }
}
