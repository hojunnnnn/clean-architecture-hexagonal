package com.hojunnnnn.hexagonal.account.adapter.out.persistence;

import com.hojunnnnn.hexagonal.account.domain.Account;
import com.hojunnnnn.hexagonal.account.domain.Activity;
import com.hojunnnnn.hexagonal.account.domain.ActivityWindow;
import com.hojunnnnn.hexagonal.account.domain.Money;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class AccountMapper {

    Account mapToDomainEntity(
        AccountEntity account,
        List<ActivityEntity> activities,
        Long withdrawalBalance,
        Long depositBalance) {

        Money baselineBalance = Money.subtract(
            Money.of(depositBalance),
            Money.of(withdrawalBalance));

        return Account.withId(
            new Account.AccountId(account.getId()),
            baselineBalance,
            maptoActivityWindow(activities));
    }

    ActivityWindow maptoActivityWindow(List<ActivityEntity> activities) {
        List<Activity> mappedActivities = new ArrayList<>();

        for (ActivityEntity activity : activities) {
            mappedActivities.add(Activity.builder()
                    .id(new Activity.ActivityId(activity.getId()))
                    .ownerAccountId(new Account.AccountId(activity.getOwnerAccountId()))
                    .sourceAccountId(new Account.AccountId(activity.getSourceAccountId()))
                    .targetAccountId(new Account.AccountId(activity.getTargetAccountId()))
                    .timestamp(activity.getTimestamp())
                    .money(Money.of(activity.getAmount()))
                .build());
        }
        return new ActivityWindow(mappedActivities);
    }

    ActivityEntity mapToJpaEntity(Activity activity) {
        return ActivityEntity.builder()
            .id(activity.getId() == null ? null : activity.getId().getValue())
            .timestamp(activity.getTimestamp())
            .ownerAccountId(activity.getOwnerAccountId().getValue())
            .sourceAccountId(activity.getSourceAccountId().getValue())
            .targetAccountId(activity.getTargetAccountId().getValue())
            .amount(activity.getMoney().getAmount().longValue())
            .build();
    }
}
