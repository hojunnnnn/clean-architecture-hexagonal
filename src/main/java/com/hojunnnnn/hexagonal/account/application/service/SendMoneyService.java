package com.hojunnnnn.hexagonal.account.application.service;

import com.hojunnnnn.hexagonal.account.application.port.in.SendMoneyCommand;
import com.hojunnnnn.hexagonal.account.application.port.in.SendMoneyUseCase;

public class SendMoneyService implements SendMoneyUseCase {

    @Override
    public boolean sendMoney(SendMoneyCommand command) {
        // TODO: 비즈니스 규칙 검증
        // TODO: 모델 상태 조작
        // TODO: 출력 값 반환
        return true;
    }
}
