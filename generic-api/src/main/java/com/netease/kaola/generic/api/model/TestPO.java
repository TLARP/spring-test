package com.netease.kaola.generic.api.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by hzwangqiqing
 * on 2017/8/10.
 */
@Data
public class TestPO implements Serializable {
    private static final long serialVersionUID = -2286368382231381434L;

    private Long id;

    private String name;
}
