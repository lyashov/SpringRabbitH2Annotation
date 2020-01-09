package com.lyaev.sender.controller;

import com.lyaev.sender.configuration.Message;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = MessageRestController.class)
class MessageRestControllerMockMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AmqpTemplate template;

    @MockBean
    private Message message;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        final String TEST_MESSAGE = "test message";
        message.setMessage(TEST_MESSAGE);

        this.mockMvc.perform(
                post("/api/message", TEST_MESSAGE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"message\":\"test message\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(TEST_MESSAGE)));
    }
}