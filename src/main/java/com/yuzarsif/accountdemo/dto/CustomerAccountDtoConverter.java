package com.yuzarsif.accountdemo.dto;

import com.yuzarsif.accountdemo.model.Account;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomerAccountDtoConverter {

    private final TransactionDtoConvert transactionDtoConvert;


    public CustomerAccountDtoConverter(TransactionDtoConvert transactionDtoConvert) {
        this.transactionDtoConvert = transactionDtoConvert;
    }

    public CustomerAccountDto convert(Account from) {
        return new CustomerAccountDto(
                from.getId(),
                from.getBalance(),
                from.getTransaction()
                        .stream()
                        .map(t -> transactionDtoConvert.convert(t))
                        .collect(Collectors.toSet()),
                        from.getCreationDate());
    }
}
