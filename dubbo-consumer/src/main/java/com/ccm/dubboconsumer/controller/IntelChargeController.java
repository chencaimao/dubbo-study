package com.ccm.dubboconsumer.controller;

import com.ccm.dubbobeans.condition.IntelChargeBaseCondition;
import com.ccm.dubboconsumer.service.IntelChargeModule.ModuleService;
import com.ccm.dubboconsumer.utils.ModuleFactoryUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chencm on 2018/12/18
 */
@RestController
public class IntelChargeController {
    public void syncOrigData(@RequestBody IntelChargeBaseCondition condition){
        ModuleService moduleService = ModuleFactoryUtils.buildFactoryByType(condition.getModuleCode());
        moduleService.syncOrigData(condition);
    }

    public void syncOrigData(@RequestParam long interChargeId){
        //todo interChargeId转condition
        IntelChargeBaseCondition condition = new IntelChargeBaseCondition();
        ModuleService moduleService = ModuleFactoryUtils.buildFactoryByType(condition.getModuleCode());
        moduleService.syncOrigData(condition);
    }
}
