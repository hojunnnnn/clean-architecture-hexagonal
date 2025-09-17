package com.hojunnnnn.hexagonal.account.application.service;

import com.hojunnnnn.hexagonal.account.application.port.in.SendMoneyCommand;
import com.hojunnnnn.hexagonal.account.application.port.in.SendMoneyUseCase;
import com.hojunnnnn.hexagonal.account.application.port.out.AccountLock;
import com.hojunnnnn.hexagonal.account.application.port.out.LoadAccountPort;
import com.hojunnnnn.hexagonal.account.application.port.out.UpdateAccountStatePort;
import com.hojunnnnn.hexagonal.account.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional
@RequiredArgsConstructor
class SendMoneyService implements SendMoneyUseCase {

    private final LoadAccountPort loadAccountPort;
    private final AccountLock accountLock;
    private final UpdateAccountStatePort updateAccountStatePort;

    @Override
    public boolean sendMoney(SendMoneyCommand command) {
        requireAccountExists(command.getSourceAccountId());
        requireAccountExists(command.getTargetAccountId());
        // TODO: 비즈니스 규칙 검증
        // TODO: 모델 상태 조작
        // TODO: 출력 값 반환
        return true;
    }

    private void requireAccountExists(Account.AccountId targetAccountId) {
        Account account = loadAccountPort.loadAccount(targetAccountId, LocalDateTime.now());
        if (account == null) {
            throw new IllegalArgumentException("Account does not exist");
        }
    }
}
