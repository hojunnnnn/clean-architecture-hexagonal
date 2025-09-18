package com.hojunnnnn.hexagonal.account.application.port.in;

import jakarta.validation.Valid;

public interface SendMoneyCommand {

    boolean sendMoney(@Valid SendMoneyRequest request);
}
