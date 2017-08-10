package com.netease.kaola.generic.provider.mapper.mysql;

import com.netease.kaola.generic.api.model.TestPO;

/**
 * Created by hzwangqiqing
 * on 2017/8/10.
 */
public interface TestMapper {

    //通过主键获取数据
    TestPO getTestbyId(Long id);

    //插入数据
    Integer insertTest(TestPO testPO);

    //通过主键删除记录
    Integer deleteTestById(Long id);

    //非空更新
    Integer updateTestBySelective(TestPO testPO);
}
