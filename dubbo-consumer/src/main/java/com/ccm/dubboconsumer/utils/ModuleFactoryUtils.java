package com.ccm.dubboconsumer.utils;

import com.ccm.dubbobeans.enums.ModuleEnum;
import com.ccm.dubboconsumer.service.IntelChargeModule.InvoiceModuleServiceImpl;
import com.ccm.dubboconsumer.service.IntelChargeModule.ModuleService;
import com.ccm.dubboconsumer.service.IntelChargeModule.SalaryModuleServiceImpl;

/**
 * Created by chencm on 2018/12/18
 * 获取模块实现
 */
public class ModuleFactoryUtils {
    public static ModuleService buildFactoryByType(int type){
        if(type == ModuleEnum.INVOICE.getModuleCode())
            return new InvoiceModuleServiceImpl();
        if(type==ModuleEnum.SALARY.getModuleCode())
            return new SalaryModuleServiceImpl();
        return new InvoiceModuleServiceImpl();

        
    }
}
