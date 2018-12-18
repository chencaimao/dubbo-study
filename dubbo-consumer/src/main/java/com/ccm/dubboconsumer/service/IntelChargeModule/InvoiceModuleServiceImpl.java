package com.ccm.dubboconsumer.service.IntelChargeModule;

import com.ccm.dubbobeans.condition.IntelChargeBaseCondition;
import com.ccm.dubbobeans.condition.InvoiceCondition;
import com.ccm.dubbobeans.dto.Page;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by chencm on 2018/12/17
 * 发票模块实现
 */
@Slf4j
public class InvoiceModuleServiceImpl implements ModuleService {
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
    @Override
    public void intelCharge(){
        try{

        }catch(Exception ex){
            log.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }
}
