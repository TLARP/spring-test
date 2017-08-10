package com.netease.kaola.generic.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by hzwangqiqing
 * on 2017/8/9.
 */
public class ProviderTest {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"classpath:application-context.xml"});
        ctx.start();
        System.in.read(); // 按任意键退出
    }
}
