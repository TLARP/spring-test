package com.netease.kaola.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by hzwangqiqing
 * on 2017/8/11.
 */
public class TestVO implements Serializable {
    private static final long serialVersionUID = 1233255437451371022L;
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
