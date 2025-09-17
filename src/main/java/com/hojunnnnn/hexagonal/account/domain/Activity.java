package com.hojunnnnn.hexagonal.account.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class Activity {

    private final ActivityId id;
    private final Account.AccountId ownerAccountId;
    private final Account.AccountId sourceAccountId;
    private final Account.AccountId targetAccountId;
    private final LocalDateTime timestamp;
    private final Money money;

    public Activity(
        Account.AccountId ownerAccountId,
        Account.AccountId sourceAccountId,
        Account.AccountId targetAccountId,
        LocalDateTime timestamp,
        Money money) {
        this.id = null;
        this.ownerAccountId = ownerAccountId;
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.timestamp = timestamp;
        this.money = money;
    }

    @Getter
    @RequiredArgsConstructor
    public static class ActivityId {
        private final Long value;
    }
}
