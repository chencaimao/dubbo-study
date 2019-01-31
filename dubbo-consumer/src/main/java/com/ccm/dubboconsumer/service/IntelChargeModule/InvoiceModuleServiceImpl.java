package com.ccm.dubboconsumer.service.IntelChargeModule;

import com.ccm.dao.UserMapper;
import com.ccm.dubbobeans.condition.IntelChargeBaseCondition;
import com.ccm.dubbobeans.condition.InvoiceCondition;
import com.ccm.dubbobeans.dto.Page;
import com.ccm.dubbobeans.entity.UserEntity;
import com.ccm.dubboconsumer.annotation.MasterDataSource;
import com.ccm.dubboconsumer.annotation.SlaveDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chencm on 2018/12/17
 * 发票模块实现
 */
@Service("InvoiceModuleServiceImpl")
@Slf4j
public class InvoiceModuleServiceImpl implements ModuleService {
    @Resource(name="SalaryModuleServiceImpl")
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
            if(intelChargeBaseCondition==null)
                return new Page<>();
            if(!(intelChargeBaseCondition instanceof InvoiceCondition))
                return new Page<>();
            InvoiceCondition condition = (InvoiceCondition)intelChargeBaseCondition;

            return new Page<>();
        }catch(Exception ex){
            log.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    /**
     * 进行智能记账
     */
    @MasterDataSource
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
