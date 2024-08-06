package com.example.usersync.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.test.RabbitListenerTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WebhookControllerTest.class)
@RabbitListenerTest
public class WebhookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testReceiveWebhook() throws Exception {
        String testPayload = "{\"message\":\"test payload\"}";

        mockMvc.perform(post("/webhook")
                .content(testPayload)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        ArgumentCaptor<String> payloadCaptor = ArgumentCaptor.forClass(String.class);
        verify(rabbitTemplate).convertAndSend(payloadCaptor.capture()); // 실제로 convertAndSend 메서드가 실행됐는지 확인

        // 캡처한 페이로드와 실제 페이로드가 일치하는지 확인
        String capturedPayload = payloadCaptor.getValue();
        assertThat(capturedPayload).isEqualTo(testPayload);
    }
}
