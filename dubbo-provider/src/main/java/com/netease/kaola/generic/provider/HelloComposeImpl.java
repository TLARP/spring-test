package com.netease.kaola.generic.provider;

import com.netease.kaola.generic.api.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hzwangqiqing
 * on 2017/8/9.
 */

@Service("helloCompose")
public class HelloComposeImpl implements HelloCompose {
    @Resource
    private TestService testService;

    @Override
    public String sayHello(List<String> accountIdList) {
        return "hello";
    }

    @Override
    public String sayHello(ParmVO parmVO) {
        return "hello";
    }
}
