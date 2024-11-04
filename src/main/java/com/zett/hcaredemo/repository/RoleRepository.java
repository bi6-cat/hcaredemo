package com.zett.hcaredemo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zett.hcaredemo.entity.Role;

public interface RoleRepository extends JpaRepository<Role, UUID>{

    Role findByName(String string);

}
