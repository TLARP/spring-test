package com.netease.kaola.controller;

import com.netease.kaola.generic.provider.HelloCompose;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hzwangqiqing
 * on 2017/8/11.
 */
@RequestMapping("/spring-test")
public class TestController {
    @RequestMapping("/page")
    @ResponseBody
    public String page() {
        return "page";
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"classpath*:application-context.xml"});
        HelloCompose helloCompose = ctx.getBean(HelloCompose.class);
        System.out.println(helloCompose.sayHello());
    }
}
