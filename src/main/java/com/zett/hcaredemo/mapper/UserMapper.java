package com.zett.hcaredemo.mapper;

import com.zett.hcaredemo.dto.auth.RegisterDTO;
import com.zett.hcaredemo.dto.auth.RoleDTO;
import com.zett.hcaredemo.dto.auth.UserDTO;
import com.zett.hcaredemo.entity.Role;
import com.zett.hcaredemo.entity.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    public static UserDTO toUserDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setRoles(user.getRoles().stream()
                .map(role -> new RoleDTO(role.getId(), role.getName(), null))
                .collect(Collectors.toSet()));
        return userDTO;
    }

    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setRoles(user.getRoles().stream()
                .map(role -> new RoleDTO(role.getId(), role.getName(), null))
                .collect(Collectors.toSet()));
        return userDTO;
    }

    public User toUser(RegisterDTO registerDTO) {
        if (registerDTO == null) {
            return null;
        }
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPhone(registerDTO.getPhone());
        return user;
    }
    public static User toUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setRoles(userDTO.getRoles().stream()
                .map(roleDTO -> new Role(roleDTO.getId(), roleDTO.getName(), null))
                .collect(Collectors.toSet()));
        return user;
    }
}
