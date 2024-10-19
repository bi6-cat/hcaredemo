package com.zett.hcaredemo.repository;

import com.zett.hcaredemo.entities.AccountRole;
import com.zett.hcaredemo.entities.AccountRole.AccountRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRoleRepository extends JpaRepository<AccountRole, AccountRoleId> {
}
