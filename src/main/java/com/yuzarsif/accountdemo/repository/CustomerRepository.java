package com.yuzarsif.accountdemo.repository;

import com.yuzarsif.accountdemo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
