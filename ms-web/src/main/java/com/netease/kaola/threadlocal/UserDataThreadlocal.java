package com.netease.kaola.threadlocal;

import com.netease.kaola.vo.UserVO;

/**
 * Created by hzwangqiqing
 * on 2017/8/17.
 */
public class UserDataThreadlocal {
    public static ThreadLocal<UserVO> threadLocal = new ThreadLocal();

    public static  String getUserName() {
        return ((UserVO) threadLocal.get()).getUserName();
    }

    public static Long getId() {
        return ((UserVO) threadLocal.get()).getId();
    }
}
