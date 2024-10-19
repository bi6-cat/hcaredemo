package com.zett.hcaredemo.service;

import java.time.Duration;
import java.util.Optional;

public interface RedisService {

    void save(String key, Object value, Duration timeout);

    void delete(String key);

    Optional<Object> get(String key);

    <T> T getOrDefault(String key, T defaultValue);

    void save(String key, String hashKey, Object value);

    void delete(String key, String hashKey);

    Optional<Object> get(String key, String hashKey);

    <T> T getOrDefault(String key, String hashKey, T defaultValue);

//    void ping();

    void save(String key, String hashKey, String value, Long timeout);

    String findOtp(String username, String hashkey);

    void clearActiveOtp(String username);

    String findToken(String username, String hashkey);
}
