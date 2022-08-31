package com.example.templates.redis;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class RedisService {


    @Cacheable(value = "name")
    public Object getName() {
        return new RedisUser();
    }
}
