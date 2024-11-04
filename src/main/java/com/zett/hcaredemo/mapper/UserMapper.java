package com.zett.hcaredemo.mapper;


import org.mapstruct.Mapper;

import com.zett.hcaredemo.dto.auth.RegisterDTO;
import com.zett.hcaredemo.dto.auth.UserDTO;
import com.zett.hcaredemo.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(User user);

    User toUser(UserDTO userDTO);

    User toUser(RegisterDTO registerDTO);
}
