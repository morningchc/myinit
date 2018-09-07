package com.myexample.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
/**
 * Created by Administrator on 2018/6/28.
 */
@RestController
@RequestMapping(value = "/order")
@RefreshScope
public class orderController {
    //servicer
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${message}")
    private String message;

    @RequestMapping(value = "/getOrder")
    public String getOrder(){
        return "OrderDetials";
    }
    @GetMapping(value = "/getMappingOrder")
    public String getMappingOrder(){
        return "NewOrderDetials";
    }
    @GetMapping("/callProdcut")
    public String callProdcut(){
        String msg = "";
        String response = "";
        //第一种方式 url固定
        RestTemplate template = new RestTemplate();
        response = template.getForObject("http://127.0.0.1:2003/product/getProduct",String.class);
        //第二种方式 url通过serviceid 获取拼
        ServiceInstance serviceInstance=loadBalancerClient.choose("EUREKA-CLIENT-PRODUCT");
        String url=String.format("http://%s:%s",serviceInstance.getHost(),serviceInstance.getPort())+"/product/getProduct";
        //第三种方式
        response= restTemplate.getForObject("http://EUREKA-CLIENT-PRODUCT/product/getProduct",String.class);
        msg = response;
        return msg;
    }
    @RequestMapping(value = "/getConfig")
    public String getConfig(){
        String env = message;
        return env;
    }
}
