package com.yuzarsif.accountdemo.repository;

import com.yuzarsif.accountdemo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
