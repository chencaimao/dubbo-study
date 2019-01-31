package com.ccm.dubboconsumer.config;

import com.ccm.dubbobeans.constants.DataSourceConstant;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by chencm on 2019/1/30
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();
    @Override
    protected Object determineCurrentLookupKey() {
        if(contextHolder.get()==null)
            return DataSourceConstant.MASTER;
        return contextHolder.get();
    }

    public static void setDataSource(String value) {
        contextHolder.set(value);
    }

    public static void clearDataSource() {
        contextHolder.remove();
    }
}
