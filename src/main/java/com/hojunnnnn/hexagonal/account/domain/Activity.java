package com.hojunnnnn.hexagonal.account.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class Activity {

    private final Account.AccountId ownerAccountId;
    private final Account.AccountId sourceAccountId;
    private final Account.AccountId targetAccountId;
    private final LocalDateTime timestamp;
    private final Money money;

}
