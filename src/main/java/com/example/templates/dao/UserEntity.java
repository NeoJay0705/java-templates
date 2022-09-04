package com.example.templates.dao;

import javax.persistence.Id;

public class UserEntity {
    private String name;
    private int age;

    // For BeanRowMapper
    public UserEntity() {}

    public UserEntity(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // For BeanRowMapper
    public void setName(String name) {
        this.name = name;
    }

    // For BeanRowMapper
    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }
}
