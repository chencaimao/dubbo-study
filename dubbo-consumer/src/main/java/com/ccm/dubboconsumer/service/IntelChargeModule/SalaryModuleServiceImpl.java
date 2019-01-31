package com.ccm.dubboconsumer.service.IntelChargeModule;

import com.ccm.dao.UserMapper;
import com.ccm.dubbobeans.condition.IntelChargeBaseCondition;
import com.ccm.dubbobeans.dto.Page;
import com.ccm.dubbobeans.entity.UserEntity;
import com.ccm.dubboconsumer.annotation.SlaveDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chencm on 2018/12/17
 * 薪酬模块实现
 */
@Service("SalaryModuleServiceImpl")
@Slf4j
public class SalaryModuleServiceImpl implements ModuleService {
    @Resource(name="InvoiceModuleServiceImpl")
    private ModuleService moduleService;
    @Autowired
    private UserMapper userMapper;
    /**
     * 同步原始数据
     */
    @Override
    public void syncOrigData(IntelChargeBaseCondition intelChargeBaseCondition){
        try{

        }catch(Exception ex){
            log.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    /**
     * 获取模块详情信息
     * @return
     */
    @Override
    public Page<?> getDetailInfo(IntelChargeBaseCondition intelChargeBaseCondition){
        try{
            return new Page<>();
        }catch(Exception ex){
            log.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    /**
     * 进行智能记账
     */
    @SlaveDataSource
    @Override
    public void intelCharge(){
        try{
            List<UserEntity> userEntityList = userMapper.findAll(new HashMap<>());
            log.debug(userEntityList.size()+"");
        }catch(Exception ex){
            log.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }
}
