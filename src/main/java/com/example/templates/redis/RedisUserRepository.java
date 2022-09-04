package com.example.templates.redis;

import javax.persistence.Cacheable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisUserRepository extends CrudRepository<RedisUser, String> {
    
}
