package com.ccm.dubboconsumer.service.IntelChargeModule;

import com.ccm.dubbobeans.condition.IntelChargeBaseCondition;
import com.ccm.dubbobeans.dto.Page;

/**
 * Created by chencm on 2018/12/17
 */
public interface ModuleService {

    /**
     * 同步原始数据
     */
    void syncOrigData(IntelChargeBaseCondition intelChargeBaseCondition);

    /**
     * 获取模块详情信息
     * @return
     */
    Page<?> getDetailInfo(IntelChargeBaseCondition intelChargeBaseCondition);

    /**
     * 进行智能记账
     */
    void intelCharge();
}
