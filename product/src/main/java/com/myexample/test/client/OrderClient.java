package com.myexample.test.client;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by Administrator on 2018/6/28.
 */
@FeignClient(name = "EUREKA-CLIENT-ORDER")
public interface OrderClient {
    @RequestMapping(value = "/order/getOrder")
    String getOrder();
    @GetMapping(value = "/order/getMappingOrder")
    String getMappingOrder();
}
