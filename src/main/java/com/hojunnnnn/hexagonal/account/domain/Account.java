package com.hojunnnnn.hexagonal.account.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Account {

    private final AccountId id;
    private final Money baselineBalance;
    private final ActivityWindow activityWindow;

    public static Account withoutId(
        Money baselineBalance,
        ActivityWindow activityWindow
    ) {
        return new Account(null, baselineBalance, activityWindow);
    }

    public static Account withId(
        AccountId accountId,
        Money baselineBalance,
        ActivityWindow activityWindow
    ) {
        return new Account(accountId, baselineBalance, activityWindow);
    }

    public Money calculateBalance() {
        return Money.add(
            this.baselineBalance,
            this.activityWindow.calculateBalance(this.id));
    }

    public boolean deposit(Money money, AccountId sourceAccountId) {
        Activity deposit = Activity.builder()
            .ownerAccountId(this.id)
            .sourceAccountId(sourceAccountId)
            .targetAccountId(this.id)
            .timestamp(LocalDateTime.now())
            .money(money)
            .build();

        this.activityWindow.addActivity(deposit);
        return true;
    }

    public boolean withdraw(Money money, AccountId targetAccountId) {
        if(!mayWithdraw(money)) {
            return false;
        }

        Activity withdrawal = Activity.builder()
            .ownerAccountId(this.id)
            .sourceAccountId(this.id)
            .targetAccountId(targetAccountId)
            .timestamp(LocalDateTime.now())
            .money(money)
            .build();

        this.activityWindow.addActivity(withdrawal);
        return true;
    }

    private boolean mayWithdraw(Money money) {
        return Money.add(
            this.calculateBalance(),
            money.negate())
            .isPositiveOrZero();
    }

    public Optional<AccountId> getId() {
        return Optional.ofNullable(this.id);
    }

    @Getter
    @RequiredArgsConstructor
    public static class AccountId {

        private final Long value;

    }
}
