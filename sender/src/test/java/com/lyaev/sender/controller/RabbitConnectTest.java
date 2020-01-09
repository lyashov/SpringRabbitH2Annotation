package com.lyaev.sender.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RabbitConnectTest {
    @Autowired
    AmqpTemplate template;

    @Test
    public void testRabbitConnect() {
        final String TEST_MESSAGE = "test message";
        try {
            template.convertAndSend("queue1", TEST_MESSAGE);
        }catch (AmqpConnectException e){
            System.out.println(e.getMessage());
            Assert.assertEquals("", e.getMessage());
        }

    }
}