package com.zett.hcaredemo.repository;

import com.zett.hcaredemo.entities.AccountRole;
import com.zett.hcaredemo.entities.AccountRole.AccountRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRoleRepository extends JpaRepository<AccountRole, AccountRoleId> {
    @Query("SELECT r.name FROM Role r JOIN AccountRole ar ON r.id = ar.roleId JOIN Account a ON ar.accountId = a.id WHERE a.username = :username")
    List<String> findRoleNamesByUsername(String username);
}
