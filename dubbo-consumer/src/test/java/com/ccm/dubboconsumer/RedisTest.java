package com.ccm.dubboconsumer;

import com.ccm.dubbobeans.dto.RedisDto;
import com.ccm.dubbobeans.enums.ResultEnum;
import com.ccm.dubbobeans.result.AjaxResult;
import com.ccm.dubbobeans.vo.UserVo;
import com.ccm.dubboconsumer.component.RedisComponent;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chencm on 2018/12/5
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes =DubboConsumerApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class RedisTest {
    @Autowired
    private RedisComponent redisComponent;
//    -----------------------optvalue---------------------
    @Test
    public void testSet(){
        try{
            //对象必须实现序列化，否则报错
            RedisDto redisDto = new RedisDto();
            redisComponent.setForValue("test",redisDto);
        }catch (Exception ex){
            log.error(ex.getMessage());
        }
    }

    @Test
    public void testGet(){
        try{
            RedisDto redisDto = redisComponent.getForValue("test");
            log.debug(redisDto.toString());
        }catch (Exception ex){
            log.error(ex.getMessage());
        }
    }

    @Test
    public void testSetListSeri(){
        try{
            //集合对象无需实现序列化
            List<UserVo> userDtoList = new ArrayList<>();
            redisComponent.setForValue("test",userDtoList);
        }catch (Exception ex){
            log.error(ex.getMessage());
        }
    }

    @Test
    public void testGetListSeri(){
        try{
            List<UserVo> userDtoList = redisComponent.getForValue("test");
            log.debug(userDtoList.toString());
        }catch (Exception ex){
            log.error(ex.getMessage());
        }
    }

    @Test
    public void testSetMapSeri(){
        try{
            //对象必须实现序列化，否则报错
            Map<String,RedisDto> userVoHashMap = new HashMap<>();
            userVoHashMap.put("x",new RedisDto());
            userVoHashMap.put("y",new RedisDto());
            redisComponent.setForValue("test",userVoHashMap);
        }catch (Exception ex){
            log.error(ex.getMessage());
        }
    }

    @Test
    public void testGetMapSeri(){
        try{
            Map<String,RedisDto> userVoHashMap = redisComponent.getForValue("test");
            log.debug(userVoHashMap.toString());
        }catch (Exception ex){
            log.error(ex.getMessage());
        }
    }
    //    -----------------------optvalue---------------------

    //    -----------------------optList---------------------
    @Test
    public void testSetList(){
        try{
            List<RedisDto> userDtoList = new ArrayList<>();
            userDtoList.add(new RedisDto());
            userDtoList.add(new RedisDto());
            redisComponent.setForList("test1",userDtoList);
        }catch (Exception ex){
            log.error(ex.getMessage());
        }
    }

    @Test
    public void testGetList(){
        try{
            List<RedisDto> userDtoList = redisComponent.getForList("test1");
            log.debug(userDtoList.toString());
        }catch (Exception ex){
            log.error(ex.getMessage());
        }
    }
    //    -----------------------optList---------------------
}
