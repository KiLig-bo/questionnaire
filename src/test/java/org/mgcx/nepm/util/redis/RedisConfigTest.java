package org.mgcx.nepm.util.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RedisConfigTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void redisTemplate() {
        redisTemplate.opsForValue().set("test_key", "test_value");
        assertEquals("test_value", redisTemplate.opsForValue().get("test_key"));
        System.out.printf("test_key: %s\n", redisTemplate.opsForValue().get("test_key"));

    }

    @Test
    void stringRedisTemplate() {
    }
}