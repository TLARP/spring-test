package com.netease.kaola.generic.provider;

import java.util.List;

/**
 * Created by hzwangqiqing
 * on 2017/8/9.
 */
public interface HelloCompose {
    String sayHello(List<String> accountIdList);

    String sayHello(ParmVO parmVO);
}
