package com.hojunnnnn.hexagonal.account.adapter.in.web;

import com.hojunnnnn.hexagonal.account.application.port.in.SendMoneyRequest;
import com.hojunnnnn.hexagonal.account.application.port.in.SendMoneyCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SendMoneyController.class)
class SendMoneyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SendMoneyCommand sendMoneyCommand;

    @Test
    void testSendMoney() throws Exception {

        mockMvc.perform(post("/accounts/send/{sourceAccountId}/{targetAccountId}/{amount}",
                41L, 42L, 500L)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        then(sendMoneyCommand).should()
            .sendMoney(any(SendMoneyRequest.class));
    }
}