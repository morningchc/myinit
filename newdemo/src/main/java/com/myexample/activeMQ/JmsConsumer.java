package com.myexample.activeMQ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/8/22.
 */
@Service
public class JmsConsumer {

    private final static Logger logger = LoggerFactory.getLogger(JmsConsumer.class);

    @JmsListener(destination = ActiveMqConfig.TOPIC,containerFactory = "jmsListenerContainerTopic")
    public void onTopicMessage(String msg) {
        logger.info("1接收到topic消息：{}",msg);
    }
    @JmsListener(destination = ActiveMqConfig.TOPIC,containerFactory = "jmsListenerContainerTopic")
    public void onTopicMessage2(String msg) {
        logger.info("2接收到topic消息：{}",msg);
    }

    @JmsListener(destination = ActiveMqConfig.QUEUE,containerFactory = "jmsListenerContainerQueue")
    public void onQueueMessage(String msg) {
        logger.info("接收到queue消息：{}",msg);
    }

}
