package com.ccm.dubbobeans.enums;

/**
 * Created by chencm on 2018/12/18
 */
public enum ModuleEnum {
    INVOICE(1,"发票"),
    SALARY(2,"薪资");
    private int moduleCode;
    private String moduleName;
    ModuleEnum(int moduleCode,String moduleName){
        this.moduleCode=moduleCode;
        this.moduleName=moduleName;
    }

    public int getModuleCode(){
        return this.moduleCode;
    }

    public String getModuleName(){
        return this.moduleName;
    }
}
