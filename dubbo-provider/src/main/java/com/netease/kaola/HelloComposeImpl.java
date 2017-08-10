package com.netease.kaola;

import org.springframework.stereotype.Service;

/**
 * Created by hzwangqiqing
 * on 2017/8/9.
 */

@Service("helloCompose")
public class HelloComposeImpl implements HelloCompose {
    public String sayHello() {
        return "hello I'm provider!";
    }
}
