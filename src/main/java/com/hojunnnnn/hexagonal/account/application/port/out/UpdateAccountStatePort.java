package com.hojunnnnn.hexagonal.account.application.port.out;

import com.hojunnnnn.hexagonal.account.domain.Account;

public interface UpdateAccountStatePort {

    void updateActivities(Account account);
}
