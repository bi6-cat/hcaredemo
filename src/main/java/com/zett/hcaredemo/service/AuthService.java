package com.zett.hcaredemo.service;

import com.zett.hcaredemo.dto.auth.ChangePasswordDTO;
import com.zett.hcaredemo.dto.auth.LoginDTO;
import com.zett.hcaredemo.dto.auth.RegisterDTO;
import com.zett.hcaredemo.dto.auth.UserDTO;

public interface AuthService {
    void changePassword(ChangePasswordDTO changePasswordDTO);
    UserDTO register(RegisterDTO registerDTO);
    boolean login(LoginDTO loginDTO);
}
