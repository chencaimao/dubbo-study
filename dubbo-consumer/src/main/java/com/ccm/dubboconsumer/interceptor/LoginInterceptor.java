package com.ccm.dubboconsumer.interceptor;

import com.ccm.dubboconsumer.utils.CurrentUserUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by chencm on 2018/12/4
 * 登录验证拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Value("${login.interceptor.enable}")
    private boolean loginEnable;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        if(loginEnable){
            if(CurrentUserUtils.getCurrentUser(request)!=null)
                return true;
            response.sendRedirect("/login.html");
            return false;
        }
        return true;
    }
}
