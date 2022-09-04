package com.example.templates.redis;

import java.time.Duration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public CacheManager cacheManager(RedisTemplate<String, Object> template) {
        //Reach for a redis cache manager
        RedisCacheManager redisCacheManager =
                RedisCacheManager.RedisCacheManagerBuilder
                        //Redis connection factory
                        .fromConnectionFactory(template.getConnectionFactory())
                        //Cache configuration
                        .cacheDefaults(getCacheConfigurationWithTtl(template, 5))
                        // Customized duration for different cacheNames
                        .withCacheConfiguration("xxx", getCacheConfigurationWithTtl(template, 1))
                        //Configure synchronous modification or deletion of put / evict
                        .transactionAware()
                        .build();

        return redisCacheManager;
    }

    RedisCacheConfiguration getCacheConfigurationWithTtl(RedisTemplate<String, Object> template, long seconds) {

        return RedisCacheConfiguration
                .defaultCacheConfig()
                //Set key to string
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(template.getStringSerializer()))
                //Set value to automatically transfer to JSON's object
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(template.getValueSerializer()))
                //Do not cache null
                .disableCachingNullValues()
                //Cache data is saved for 1 hour
                .entryTtl(Duration.ofSeconds(seconds));
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("10.5.0.5", 6379);
        return new JedisConnectionFactory(config);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
}
