package com.myexample.controller;

import com.myexample.activeMQ.JmsProducer;
import javax.jms.Queue;
import com.myexample.model.po.Fruit;
import com.myexample.redis.RedisDao;
import com.myexample.service.WelcomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


/**
 * Created by chc on 2018/7/19.
 */
@RestController
@RequestMapping(value = "/test")
public class Welcome {
    public static Logger logger= LoggerFactory.getLogger(Welcome.class);

    @Autowired
    private RedisDao redisDao;
    @Autowired
    private WelcomeService welcomeService;
    @Autowired
    private JmsProducer jmsProducer;
    @Autowired
    private Queue queue;

    @RequestMapping(value = "/mytest")
    public String welcome() {
        String mes = "Welcome chc";
        redisDao.newsetKey("name","chc");
        redisDao.newsetKey("age","11");
        redisDao.newsetKey("sex","man");
        return mes;
    }
    @RequestMapping(value = "/getmytest")
    public void getRedisTest(){
        logger.info(redisDao.getValue("name"));
        logger.info(redisDao.getValue("age"));
        logger.info(redisDao.getValue("sex"));
        logger.info(redisDao.newgetValue("sex"));
    }

    @GetMapping(value = "/getAlltest")
    public ArrayList<Fruit> getAlltest() {
        ArrayList<Fruit> list = new ArrayList<Fruit>();
        try {
           list = (ArrayList<Fruit>) redisDao.getObject("fruts", ArrayList.class);
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @GetMapping(value = "/getAll")
    public ArrayList<Fruit> getAll() {
        //ArrayList<Fruit> list =welcomeService.getAll();
        Fruit fruit = new Fruit();
        fruit.setName("桃");
        ArrayList<Fruit> list = welcomeService.query(fruit, 1, 0);//pagehelper 不分页
        redisDao.setObjectTest("fruits",list);
        return list;
    }

    @PostMapping(value = "/queryFruit/{pageIndex}/{pageSize}")
    public ArrayList<Fruit> queryFruit(@RequestBody Fruit query, @PathVariable int pageIndex, @PathVariable int pageSize) {
        ArrayList<Fruit> list = welcomeService.query(query, pageIndex, pageSize);
        return list;
    }

    @PostMapping(value = "/queryFruitNoPage")
    public ArrayList<Fruit> queryFruitNoPage(@RequestBody Fruit query) {
        ArrayList<Fruit> list = welcomeService.query(query, 1, 0);//pagehelper 不分页
        return list;
    }

    @RequestMapping(value = "/testActiveMq")
    public String testActiveMq(){
        for(int i=0;i<10;i++){
            jmsProducer.sendMessage(queue,"QueueMessage"+i);
        }
        return "testActiveMq";
    }
}