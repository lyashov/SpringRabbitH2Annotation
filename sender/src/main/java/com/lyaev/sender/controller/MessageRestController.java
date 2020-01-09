package com.lyaev.sender.controller;

import com.lyaev.sender.configuration.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageRestController.class);

    @Autowired
    AmqpTemplate template;

    @Autowired
    Message msg;

    /**
     * @CrossOrigin spring's annotation used for cross domain requests.
     * @return start page
     */
    @CrossOrigin
    @RequestMapping("/")
    public @ResponseBody
    ResponseEntity<Message> greeting() {
        msg.setMessage("This service has API at: http://hostname/api/message");
        return new ResponseEntity<Message>(msg, HttpStatus.OK);
    }

    /**
     * @CrossOrigin spring's annotation used for cross domain requests.
     * Get a message from request parameter and send the message through sender service.
     * @return received message
     */
    @CrossOrigin
    @RequestMapping({"/api/message"})
    public @ResponseBody ResponseEntity<Message> postMessage(@RequestBody Message message) {
        LOGGER.info("Run controller /api/message. Message: ".concat(message.getMessage()));
        if (message != null) {
            template.convertAndSend("queue1",message.getMessage());
            return new ResponseEntity<Message>(message, HttpStatus.OK);
        }
        return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
    }
}