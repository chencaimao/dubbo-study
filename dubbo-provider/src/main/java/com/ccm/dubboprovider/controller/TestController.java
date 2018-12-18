package com.ccm.dubboprovider.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ccm.dubboapi.OrderService;
import com.ccm.dubbobeans.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chencm on 2018/11/29
 * 测试dubbo
 */
@RestController
@Slf4j
public class TestController {
    @Reference
    private OrderService orderService;

    @GetMapping("/test")
    public void test(){
        OrderDto orderDto = orderService.getOrderInfo();
        log.info(orderDto.getOrderId());
    }
}
