package com.ccm.dubboconsumer.aspect;

import com.ccm.dubbobeans.constants.DataSourceConstant;
import com.ccm.dubboconsumer.annotation.SlaveDataSource;
import com.ccm.dubboconsumer.config.DynamicDataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by chencm on 2019/1/30
 */
@Component
@Aspect
public class DataSourceAspect {
    @Pointcut("@annotation(com.ccm.dubboconsumer.annotation.SlaveDataSource)")
    public void DatasourceExpression() {

    }

    @Around("DatasourceExpression()")
    public Object around(ProceedingJoinPoint point){
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        SlaveDataSource ds = method.getAnnotation(SlaveDataSource.class);
        if (ds == null) {
            DynamicDataSource.setDataSource(DataSourceConstant.MASTER);
        } else {
            DynamicDataSource.setDataSource(DataSourceConstant.SLAVE);
        }
        Object obj = null;
        try {
            obj = point.proceed();
        } catch (Throwable throwable) {
        }finally {
            DynamicDataSource.clearDataSource();
        }
        return obj;
    }
}
