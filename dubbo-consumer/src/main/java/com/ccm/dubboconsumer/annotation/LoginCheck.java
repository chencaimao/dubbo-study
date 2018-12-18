package com.ccm.dubboconsumer.annotation;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.*;

/**
 * Created by chencm on 2018/12/4
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Deprecated
public @interface LoginCheck {
}
