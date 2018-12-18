package com.ccm.dubboconsumer.utils;

import com.ccm.dubbobeans.constants.SessionConstant;
import com.ccm.dubbobeans.dto.LoginDto;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by chencm on 2018/12/4
 */
public class CurrentUserUtils {
    public static LoginDto getCurrentUser(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session==null||session.getAttribute(SessionConstant.LOGIN_NAME)==null)
            return null;
        LoginDto loginDto = new LoginDto();
        loginDto.setUserId(session.getAttribute(SessionConstant.LOGIN_ID).toString());
        loginDto.setUserName(session.getAttribute(SessionConstant.LOGIN_NAME).toString());
        return loginDto;
    }
}
