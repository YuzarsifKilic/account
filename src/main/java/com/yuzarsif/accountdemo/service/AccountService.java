package com.yuzarsif.accountdemo.service;

import com.yuzarsif.accountdemo.dto.AccountDto;
import com.yuzarsif.accountdemo.dto.AccountDtoConverter;
import com.yuzarsif.accountdemo.dto.CreateAccountRequest;
import com.yuzarsif.accountdemo.model.Account;
import com.yuzarsif.accountdemo.model.Customer;
import com.yuzarsif.accountdemo.model.Transaction;
import com.yuzarsif.accountdemo.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private AccountDtoConverter accountDtoConverter;

    public AccountService(AccountRepository accountRepository,
                          CustomerService customerService,
                          AccountDtoConverter accountDtoConverter) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.accountDtoConverter = accountDtoConverter;
    }

    public AccountDto createAccount(CreateAccountRequest request) {
        Customer customer = customerService.findCustomerById(request.getCustomerId());

        Account account = new Account(
                customer,
                request.getInitialCredit(),
                LocalDateTime.now());

        if (request.getInitialCredit().compareTo(BigDecimal.ZERO) > 0) {
            Transaction transaction = new Transaction(
                    request.getInitialCredit(),
                    LocalDateTime.now(),
                    account);

            account.getTransaction().add(transaction);
        }
        return accountDtoConverter.convert(accountRepository.save(account));
    }
}
