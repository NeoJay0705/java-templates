package com.example.templates.cassandra;

import javax.persistence.Id;

public class Users {
    @Id
    private String name;
    private int age3;
    public Users(String name, int age3) {
        this.name = name;
        this.age3 = age3;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge3() {
        return age3;
    }
    public void setAge3(int age) {
        this.age3 = age;
    }
}
