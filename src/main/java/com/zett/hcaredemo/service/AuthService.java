package com.zett.hcaredemo.service;

import com.zett.hcaredemo.dto.auth.RegisterDTO;
import com.zett.hcaredemo.dto.auth.UserDTO;

public interface AuthService {
    UserDTO register(RegisterDTO registerDTO);
}
