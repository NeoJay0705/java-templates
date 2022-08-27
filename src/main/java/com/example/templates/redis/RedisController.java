package com.example.templates.redis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("redis")
public class RedisController {

    private RedisUserRepository userRepos;

    public RedisController(RedisUserRepository userRepos) {
        this.userRepos = userRepos;
    }

    @RequestMapping("find")
    public Object find() {
        List<Object> result = new ArrayList<>();
        this.userRepos.findAll().forEach(result::add);
        return result;
    }

    @RequestMapping("put")
    public void put() {
        RedisUser user = new RedisUser();
        user.setName("neo");
        user.setAge("55");
        this.userRepos.save(user);
    }

    public void delete() {

    }
}
