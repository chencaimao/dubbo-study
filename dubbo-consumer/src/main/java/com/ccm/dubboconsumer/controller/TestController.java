package com.ccm.dubboconsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ccm.dubboapi.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by chencm on 2018/11/27
 * 测试dubbo
 */
@Api(value = "测试dubbo")
@RestController
@Slf4j
public class TestController {
    @Reference
    private TestService testService;

    @ApiOperation(value = "test")
    @GetMapping("/test")
    public void test(){
        log.info(testService.getInfo());
    }
}
