package com.ccm.dubboconsumer.controller;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;

/**
 * Created by chencm on 2018/12/13
 */
@RestController
@RequestMapping("/activeMq")
@Configuration
public class ActiveMqController {

    @Value("${activemq.queue.name}")
    private String queueName;
    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    /**
     * 模拟发送activemq队列消息
     * @param message 消息
     */
    @GetMapping("/send")
    public void send(@RequestParam("message") String message){
        Destination destination = new ActiveMQQueue(queueName);
        jmsTemplate.convertAndSend(destination,message);
    }
}
