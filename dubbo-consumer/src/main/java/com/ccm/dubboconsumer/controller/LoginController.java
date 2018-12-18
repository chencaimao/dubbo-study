package com.ccm.dubboconsumer.controller;

import com.ccm.dao.UserMapper;
import com.ccm.dubbobeans.constants.SessionConstant;
import com.ccm.dubbobeans.dto.LoginDto;
import com.ccm.dubbobeans.dto.RedisDto;
import com.ccm.dubbobeans.entity.UserEntity;
import com.ccm.dubbobeans.enums.ResultEnum;
import com.ccm.dubbobeans.result.AjaxResult;
import com.ccm.dubbobeans.vo.UserVo;
import com.ccm.dubboconsumer.component.RedisComponent;
import com.ccm.dubboconsumer.component.WebSocketInfoCompent;
import com.ccm.dubboconsumer.utils.CurrentUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chencm on 2018/12/4
 * 模拟登录-session模式
 */
@Api(value = "模拟登录-session模式")
@RestController
@Slf4j
public class LoginController {

    @Autowired
    private UserMapper userMapper;

    @ApiOperation("登录")
    @GetMapping("/login")
    public AjaxResult<?> login(HttpServletRequest request, @ApiParam(value = "用户名", required = true) @RequestParam("userName") final String userName,@ApiParam(value = "密码", required = true) @RequestParam("userPwd") final String userPwd){
        try{
            Map<String,Object> map = new HashMap<>();
            map.put("userName",userName);
            UserEntity userEntity = userMapper.findFirst(map);
            if(userEntity==null)
                return new AjaxResult<>("用户不存在");
            if(!userEntity.getUserPwd().equals(userPwd))
                return new AjaxResult<>("用户密码不正确");
            //得到session对象，没有则新建
            HttpSession session = request.getSession();
            session.setAttribute(SessionConstant.LOGIN_NAME,userName);
            session.setAttribute(SessionConstant.LOGIN_ID,userEntity.getUserId());
            return new AjaxResult<>(ResultEnum.SUCCESS);
        }catch (Exception ex){
            return new AjaxResult<>(ex.getMessage());
        }


    }

    @ApiOperation("登出")
    @GetMapping("/logout")
    public AjaxResult<?> logout(HttpServletRequest request){
        try{
            //得到session对象
            HttpSession session = request.getSession(false);
            if(session!=null)
                session.invalidate();
            return new AjaxResult<>(ResultEnum.SUCCESS);
        }catch (Exception ex){
            return new AjaxResult<>(ex.getMessage());
        }

    }

    @ApiOperation("获取当前用户")
    @GetMapping("/getCurrentUser")
    public AjaxResult<?> getCurrentUser(HttpServletRequest request){
        try{
            //return new AjaxResult<>(CurrentUserUtils.getCurrentUser(request));
            return new AjaxResult<>(CurrentUserUtils.getCurrentUser(request));
        }catch (Exception ex){
            return new AjaxResult<>(ex.getMessage());
        }
    }

    @ApiOperation("判断当前用户是否已经上线")
    @GetMapping("/userOnline")
    public AjaxResult<?> getCurrentUser(@RequestParam("userId") String userId){
        try{
            if(WebSocketInfoCompent.webSocketMap.containsKey(userId))
                return new AjaxResult<>(true);
            return new AjaxResult<>(false);
        }catch (Exception ex){
            return new AjaxResult<>(ex.getMessage());
        }
    }

}
