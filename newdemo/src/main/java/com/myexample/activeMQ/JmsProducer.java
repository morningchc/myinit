package com.myexample.activeMQ;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * Created by Administrator on 2018/8/22.
 */
@Service
public class JmsProducer {
    @Autowired
    private JmsTemplate jmsTemplate;
    public static Logger logger= LoggerFactory.getLogger(JmsProducer.class);

    public void sendMessage(Destination destination, String message) {
        if(destination instanceof ActiveMQQueue){
            logger.info("向queue发送 "+ message);
        }else if(destination instanceof ActiveMQTopic){
            logger.info("topic发布"+message);
        }
        this.jmsTemplate.convertAndSend(destination,message);
    }
}
