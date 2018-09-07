//package com.myexample.activeMQ;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.jms.annotation.JmsListener;
//import org.springframework.stereotype.Service;
//
//import java.util.Timer;
//
//
///**
// * Created by Administrator on 2018/8/21.
// */
//@Service
//public class Consumer {
//    public static Logger logger= LoggerFactory.getLogger(Consumer.class);
//    @JmsListener(destination = "chc")
//    public void receive(String message) {
//        logger.info("收到的 message 是：" + message);
//    }
//}
