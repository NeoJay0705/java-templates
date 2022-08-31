package com.example.templates.redis;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("redis")
public class RedisController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private RedisUserRepository userRepos;
    private RedisService rs;

    public RedisController(RedisUserRepository userRepos, RedisService rs) {
        this.userRepos = userRepos;
        this.rs = rs;
    }

    @Cacheable(value = "yyy", key = "'ys '+#x+#y", condition = "#x > 100") 
    @RequestMapping("find/5/{x}/{y}")
    public Object find1(@PathVariable int x, @PathVariable int y) {
        // List<Object> result = new ArrayList<>();
        // this.userRepos.findAll().forEach(result::add);
        logger.info("aaa %s", "bbbb");

        return "123";
    }

    @Cacheable(value = "xxx", key = "'xs '+#x+#y", condition = "#x > 100") 
    @RequestMapping("find/{x}/{y}")
    public Object find(@PathVariable int x, @PathVariable int y) {
        // List<Object> result = new ArrayList<>();
        // this.userRepos.findAll().forEach(result::add);
        return "" + x + y;
    }

    @CachePut(value = "xxx")
    @RequestMapping("put/{x}/{y}/{cont}")
    public Object put(@PathVariable int x, @PathVariable int y, @PathVariable String cont) {
        return cont + x + y;
    }

    @CacheEvict()
    public void delete() {

    }

    @CacheEvict(value = "xxx", key = "'xs '+#x+#y")
    @RequestMapping("del/{x}/{y}")
    public void evictSingleCacheValue(@PathVariable int x, @PathVariable int y) {}
}
