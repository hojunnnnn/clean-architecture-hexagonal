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
            mappedActivities.add(new Activity(
                new Activity.ActivityId(activity.getId()),
                new Account.AccountId(activity.getOwnerAccountId()),
                new Account.AccountId(activity.getSourceAccountId()),
                new Account.AccountId(activity.getTargetAccountId()),
                activity.getTimestamp(),
                Money.of(activity.getAmount())));
        }
        return new ActivityWindow(mappedActivities);
    }

    ActivityEntity mapToJpaEntity(Activity activity) {
        return new ActivityEntity(
            activity.getId() == null ? null : activity.getId().getValue(),
            activity.getTimestamp(),
            activity.getOwnerAccountId().getValue(),
            activity.getSourceAccountId().getValue(),
            activity.getTargetAccountId().getValue(),
            activity.getMoney().getAmount().longValue());
    }
}
