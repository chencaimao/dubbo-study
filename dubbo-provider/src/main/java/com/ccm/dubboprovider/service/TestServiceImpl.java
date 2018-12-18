package com.ccm.dubboprovider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.ccm.dubboapi.TestService;

/**
 *  Created by chencm on 2018/11/27
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    public String getInfo(){
        return "ccm";
    }
}