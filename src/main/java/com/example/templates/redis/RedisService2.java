package com.example.templates.redis;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class RedisService2 {
    @Cacheable(value = "cache")
    public String getCache(String a, String b) {
        return "neo" + a + b;
    }
}
