package com.hojunnnnn.hexagonal.account.application.port.out;

import com.hojunnnnn.hexagonal.account.domain.Account;

public interface AccountLock {

    void lockAccount(Account.AccountId accountId);

    void releaseAccount(Account.AccountId accountId);

}
