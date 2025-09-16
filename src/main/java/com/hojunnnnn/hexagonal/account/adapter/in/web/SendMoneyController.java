package com.hojunnnnn.hexagonal.account.adapter.in.web;

import com.hojunnnnn.hexagonal.account.application.port.in.SendMoneyCommand;
import com.hojunnnnn.hexagonal.account.application.port.in.SendMoneyUseCase;
import com.hojunnnnn.hexagonal.account.domain.Account;
import com.hojunnnnn.hexagonal.account.domain.Money;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class SendMoneyController {

    private final SendMoneyUseCase sendMoneyUseCase;

    @PostMapping("/accounts/send/{sourceAccountId}/{targetAccountId}/{amount}")
    public ResponseEntity<Void> sendMoney(
        @PathVariable("sourceAccountId") Long sourceAccountId,
        @PathVariable("targetAccountId") Long targetAccountId,
        @PathVariable("amount") Long amount
    ) {

        SendMoneyCommand command = SendMoneyCommand.builder()
            .sourceAccountId(new Account.AccountId(sourceAccountId))
            .targetAccountId(new Account.AccountId(targetAccountId))
            .money(Money.of(amount))
            .build();

        sendMoneyUseCase.sendMoney(command);

        return ResponseEntity.ok().build();
    }
}
