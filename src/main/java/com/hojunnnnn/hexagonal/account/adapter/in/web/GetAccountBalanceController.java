package com.hojunnnnn.hexagonal.account.adapter.in.web;

import com.hojunnnnn.hexagonal.account.application.port.in.GetAccountBalanceQuery;
import com.hojunnnnn.hexagonal.account.domain.Account;
import com.hojunnnnn.hexagonal.account.domain.Money;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
class GetAccountBalanceController {

    private final GetAccountBalanceQuery getAccountBalanceQuery;

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<Long> getBalance(@PathVariable("accountId") Long accountId) {

        Money balance = getAccountBalanceQuery.getAccountBalance(new Account.AccountId(accountId));
        return ResponseEntity.ok(balance.toLong());
    }
}
