package com.ccm.dubbobeans.condition;

import lombok.Data;

/**
 * Created by chencm on 2018/12/18
 * 智能结账模块查询condition
 */
@Data
public class IntelChargeBaseCondition {
    /**
     * 账期
     */
    private String accountPeriod;

    /**
     * 账套代码
     */
    private long ztdm;

    /**
     * 模块编码
     */
    private int moduleCode;
}
