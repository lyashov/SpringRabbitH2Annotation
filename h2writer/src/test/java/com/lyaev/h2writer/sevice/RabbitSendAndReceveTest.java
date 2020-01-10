package com.lyaev.h2writer.sevice;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableRabbit
class RabbitSendAndReceveTest {
    final String TEST_MESSAGE = "test message";

    @Autowired
    AmqpTemplate template;

    @RabbitListener(queues = "queueTest")
    void receiveMes(String message){
        Assert.assertEquals(message, TEST_MESSAGE);
    }

    @Test
    void processRabbitMQTest() {
        template.convertAndSend("queueTest", TEST_MESSAGE);
    }
}