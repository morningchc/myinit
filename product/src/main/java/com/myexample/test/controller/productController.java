package com.myexample.test.controller;

import com.myexample.test.client.OrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/6/28.
 */
@RestController
@RequestMapping(value = "/product")
public class productController {
    @Autowired
    OrderClient orderClient;

    @RequestMapping(value = "/getProduct")
    public String getOrder(){
        return "ProductDetials";
    }

    @RequestMapping(value = "/callOrder")
    public String callOrder(){
        String msg = "";
        //msg = orderClient.getMappingOrder();
        msg = orderClient.getOrder();
        return msg;
    }
}

