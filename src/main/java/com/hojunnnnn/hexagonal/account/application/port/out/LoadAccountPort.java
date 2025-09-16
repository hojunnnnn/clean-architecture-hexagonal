package com.hojunnnnn.hexagonal.account.application.port.out;

import com.hojunnnnn.hexagonal.account.domain.Account;

import java.time.LocalDateTime;

public interface LoadAccountPort {

    Account loadAccount(Account.AccountId accountId, LocalDateTime baselineDate);
}
