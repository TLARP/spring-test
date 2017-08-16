package com.netease.kaola.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hzwangqiqing
 * on 2017/8/11.
 */
@RequestMapping("/spring-test")
@Controller
public class TestController {
    @RequestMapping("/page")
    @ResponseBody
    public String page() {
        return "page";
    }
}
