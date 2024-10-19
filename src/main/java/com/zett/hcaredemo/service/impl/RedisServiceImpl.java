package com.zett.hcaredemo.service.impl;

import com.zett.hcaredemo.service.RedisService;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final StringRedisTemplate stringRedisTemplate;

    public RedisServiceImpl(RedisTemplate<String, Object> redisTemplate, StringRedisTemplate stringRedisTemplate) {
        this.redisTemplate = redisTemplate;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void save(String key, Object value, Duration timeout) {
        redisTemplate.opsForValue().set(key, value, timeout);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public Optional<Object> get(String key) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(key));
    }

    @Override
    public <T> T getOrDefault(String key, T defaultValue) {
        Object result = redisTemplate.opsForValue().get(key);
        return (result != null) ? (T) result : defaultValue;
    }

    @Override
    public void save(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    @Override
    public void delete(String key, String hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }

    @Override
    public Optional<Object> get(String key, String hashKey) {
        return Optional.ofNullable(redisTemplate.opsForHash().get(key, hashKey));
    }

    @Override
    public <T> T getOrDefault(String key, String hashKey, T defaultValue) {
        Object result = redisTemplate.opsForHash().get(key, hashKey);
        return (result != null) ? (T) result : defaultValue;
    }

//    @Override
//    public void ping() {
//        if (redisTemplate == null) {
//            return;
//        }
//
//        RedisConnectionFactory factory = redisTemplate.getConnectionFactory();
//        if (factory == null) {
//            System.out.println("NULLLLLLLLLLLLLLLLLLLLLLLL");
//            return;
//        }
//
//        RedisConnection connection = factory.getConnection();
//
//        connection.ping();
//    }


    @Override
    public void save(String key, String hashKey, String value, Long timeout) {
        HashOperations<String, Object, Object> hashOps = stringRedisTemplate.opsForHash();
        hashOps.put(key, hashKey, value);
        stringRedisTemplate.expire(key, timeout, TimeUnit.MINUTES);
    }
    @Override
    public String findOtp(String username, String hashKey) {
        return getValue(username, hashKey);
    }
    @Override
    public void clearActiveOtp(String username) {
        stringRedisTemplate.delete(username);
    }
    @Override
    public String findToken(String username, String hashKey) {
        return getValue(username, hashKey);
    }
    public String getValue(String key, String hashKey) {
        HashOperations<String, Object, Object> hashOps = stringRedisTemplate.opsForHash();
        return (String) hashOps.get(key, hashKey);
    }
}
