package com.netease.kaola.controller;

import com.netease.kaola.vo.TestVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/page2")
    @ResponseBody
    public Map<String, Object> page2() {
        Map<String, Object> map = new HashMap<>();
        map.put("retCode", "200");
        map.put("retDesc", "操作成功");
        return map;
    }

    @RequestMapping("/page3")
    @ResponseBody
    public TestVO page3() {
        TestVO testVO = new TestVO();
        testVO.setId(1L);
        testVO.setName("chaos");
        return testVO;
    }
}
