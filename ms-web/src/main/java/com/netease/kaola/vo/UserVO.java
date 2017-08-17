package com.netease.kaola.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by hzwangqiqing
 * on 2017/8/17.
 */
@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = 562531715883808594L;

    //账户名称
    private String userName;

    //账户id
    private Long id;
}
