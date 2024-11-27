package com.zett.hcaredemo.service;

import com.zett.hcaredemo.entity.User;

public interface UserService {
    User findByUsername(String username);
}
