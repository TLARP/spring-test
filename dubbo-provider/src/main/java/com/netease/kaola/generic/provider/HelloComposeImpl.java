package com.netease.kaola.generic.provider;

import com.netease.kaola.generic.api.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hzwangqiqing
 * on 2017/8/9.
 */

@Service("helloCompose")
public class HelloComposeImpl implements HelloCompose {
    @Resource
    private TestService testService;

    public String sayHello() {
        System.out.println(testService.getTestbyId(1L));
        return "hello I'm provider!";
    }
}
