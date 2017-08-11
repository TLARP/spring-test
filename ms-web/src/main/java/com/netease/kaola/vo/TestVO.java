package com.netease.kaola.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by hzwangqiqing
 * on 2017/8/11.
 */
@Data
public class TestVO implements Serializable {
    private static final long serialVersionUID = 1233255437451371022L;
    private Long id;

    private String name;
}
