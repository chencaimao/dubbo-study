package com.ccm.dubboconsumer.component;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

/**
 * Created by chencm on 2018/12/13
 */
@Component
@Slf4j
public class ActiveMqComponent {
    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    @Value("${activemq.queue.name}")
    private String queueName;

    @Value("${activemq.topic.name}")
    private String topicName;

    /**
     * 队列模式（点对点）
     * @throws Exception
     */
    @Scheduled(cron = "*/2 * * * * ?")
    public void sendScheduleQueue() throws Exception{
        log.info("sendScheduleQueue()开始执行："+new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
        Destination destination = new ActiveMQQueue(queueName);
        jmsTemplate.convertAndSend(destination,"queue" + new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 订阅发布模式（并发消费）
     * @throws Exception
     */
    @Scheduled(cron = "*/2 * * * * ?")
    public void sendScheduleTopic() throws Exception{
        log.info("sendScheduleTopic()开始执行："+new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
        Destination destination = new ActiveMQTopic(topicName);
        jmsTemplate.convertAndSend(destination,"topic"+new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
    }

    @JmsListener(destination = "test.queue",containerFactory = "queueListenerFactory")
    public void receiveMsg(String message){
        log.info("收到消息："+message);
    }

    @JmsListener(destination = "test.topic",containerFactory = "topicListenerFactory")
    public void receiveMsg1(String message){
        log.info("收到消息1："+message);
    }

    @JmsListener(destination = "test.topic",containerFactory = "topicListenerFactory")
    public void receiveMsg2(String message){
        log.info("收到消息2："+message);
    }

}
