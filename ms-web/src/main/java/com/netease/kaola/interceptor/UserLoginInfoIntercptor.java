package com.netease.kaola.interceptor;

import com.netease.kaola.threadlocal.UserDataThreadlocal;
import com.netease.kaola.vo.UserVO;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hzwangqiqing
 * on 2017/8/17.
 */
public class UserLoginInfoIntercptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        UserVO userVO = new UserVO();
        userVO.setUserName((String) httpServletRequest.getAttribute("userName"));
        userVO.setId((Long) httpServletRequest.getAttribute("id"));
        UserDataThreadlocal.threadLocal.set(userVO);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
