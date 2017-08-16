package com.netease.kaola.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

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


    @RequestMapping("/page1")
    @ResponseBody
    public List<Long> page1() {
        return Arrays.asList(new Long[]{1L, 2L, 3L});
    }

}
