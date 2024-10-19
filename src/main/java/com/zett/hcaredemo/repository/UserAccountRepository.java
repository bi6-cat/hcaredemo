package com.zett.hcaredemo.repository;

import com.zett.hcaredemo.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, UserAccount.UserAccountId> {
}
