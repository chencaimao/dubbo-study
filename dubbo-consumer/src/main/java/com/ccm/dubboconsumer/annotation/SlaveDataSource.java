package com.ccm.dubboconsumer.annotation;

import java.lang.annotation.*;

/**
 * Created by chencm on 2019/1/30
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SlaveDataSource {
}
