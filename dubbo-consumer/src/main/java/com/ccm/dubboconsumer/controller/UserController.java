package com.ccm.dubboconsumer.controller;

import com.ccm.dao.UserMapper;
import com.ccm.dubbobeans.entity.UserEntity;
import com.ccm.dubbobeans.enums.ResultEnum;
import com.ccm.dubbobeans.result.AjaxResult;
import com.ccm.dubbobeans.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chencm on 2018/12/5
 * 用户模块
 */
@Api(value = "用户")
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @ApiOperation("新增用户")
    @PostMapping("/insert")
    public AjaxResult<?> insert(@ApiParam(value = "用户信息", required = true)@RequestBody UserVo userVo){
        try{
            UserEntity userEntity = new UserEntity();
            BeanUtils.copyProperties(userVo,userEntity);
            int retCode = userMapper.insert(userEntity);
            if(retCode>0)
                return new AjaxResult<>(ResultEnum.ERROR_UNKNOWN);
            return new AjaxResult<>(ResultEnum.SUCCESS);
        }catch (Exception ex){
            return new AjaxResult<>(ex.getMessage());
        }
    }
}
