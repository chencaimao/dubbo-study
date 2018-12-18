package com.ccm.dubboconsumer.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.ccm.dubboapi.OrderService;
import com.ccm.dubbobeans.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;


/**
 * Created by chencm on 2018/11/29
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public OrderDto getOrderInfo(){
        try{
            OrderDto orderDto = new OrderDto();
            orderDto.setOrderId("1");
            return orderDto;
        }catch (RuntimeException ex){
            throw new RuntimeException(ex.getMessage(),ex);
        }
    }

    public static void main(String[] args){
        try{
            List<Integer> valList = new Vector<>();
            final CountDownLatch countDownLatch = new CountDownLatch(5);
            for(int i =0;i<5;i++){
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            String th_name = Thread.currentThread().getName();
                            log.info("线程："+th_name+"开始执行");
                            for (int i=0;i<5;i++){
                                valList.add(1);
                                Thread.sleep(1000);
                            }
                            log.info("线程："+th_name+"执行完毕");
                        } catch (Exception e) {
                            throw new RuntimeException(e.getMessage());
                        }finally {
                            countDownLatch.countDown();
                        }
                    }
                }.start();
            }
            countDownLatch.await();
            log.info("集合数量："+valList.size());
        }catch (Exception ex){
            log.error(ex.getMessage());
        }
    }
}
