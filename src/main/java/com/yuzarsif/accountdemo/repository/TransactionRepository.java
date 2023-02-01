package com.yuzarsif.accountdemo.repository;

import com.yuzarsif.accountdemo.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
