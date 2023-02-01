package com.yuzarsif.accountdemo.dto

import java.math.BigDecimal
import java.time.LocalDateTime

data class CustomerDto(

        val id: String?,
        val name: String?,
        val surname: String?,
        val accounts: Set<CustomerAccountDto>
)
