//package com.myexample.activeMQ;
//
//import org.apache.activemq.command.ActiveMQQueue;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.stereotype.Service;
//
//import javax.jms.Destination;
//
///**
// * Created by Administrator on 2018/8/21.
// */
//@Service
//public class Producer {
//    @Autowired
//    private JmsTemplate jmsTemplate;
//
//    public static Logger logger= LoggerFactory.getLogger(Producer.class);
//
//    public void send(String destinationName,String message){
//        //message = "chc";
//        Destination destination = new ActiveMQQueue(destinationName);
//        jmsTemplate.setPubSubDomain(false);
//        jmsTemplate.convertAndSend(destination, message);
//        logger.info("发送的 message 是：" + message);
//    }
//}
