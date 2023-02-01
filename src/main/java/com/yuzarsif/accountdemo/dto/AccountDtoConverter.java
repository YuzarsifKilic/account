package com.yuzarsif.accountdemo.dto;

import com.yuzarsif.accountdemo.model.Account;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AccountDtoConverter {

    private final CustomerDtoConverter customerDtoConverter;
    private final TransactionDtoConvert transactionDtoConvert;

    public AccountDtoConverter(CustomerDtoConverter customerDtoConverter, TransactionDtoConvert transactionDtoConvert) {
        this.customerDtoConverter = customerDtoConverter;
        this.transactionDtoConvert = transactionDtoConvert;
    }

    public AccountDto convert(Account from) {
        return new AccountDto(
                from.getId(),
                from.getBalance(),
                from.getCreationDate(),
                customerDtoConverter.convertToAccountCustomer(from.getCustomer()),
                from.getTransaction().stream().map(t -> transactionDtoConvert.convert(t)).collect(Collectors.toSet()));
    }
}
