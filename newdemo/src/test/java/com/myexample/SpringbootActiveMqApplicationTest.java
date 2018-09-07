package com.myexample;

//import com.myexample.activeMQ.Consumer;
import com.myexample.activeMQ.JmsProducer;
//import com.myexample.activeMQ.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Queue;
import javax.jms.Topic;


/**
 * Created by Administrator on 2018/8/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootActiveMqApplicationTest {
    public static Logger logger= LoggerFactory.getLogger(SpringbootRedisApplicationTests.class);
    @Test
    public void contextLoads() {

    }

    @Autowired
    private JmsProducer jmsProducer;

    @Autowired
    Queue queue;

    @Autowired
    Topic topic;

    @Test
    public void testActiveMq(){
        for(int i=0;i<3;i++){
            jmsProducer.sendMessage(queue,"QueueMessage"+i);
        }

        jmsProducer.sendMessage(topic,"topicMessage");
    }


//    @Autowired
//    Producer producer;
//
//    @Test
//    public void testActiveMq(){
//        for(int i=0;i<3;i++){
//            producer.send("chc","message"+i);
//        }
//    }

}
