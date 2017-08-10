package com.netease.kaola.generic.provider.serviceimpl;

import com.netease.kaola.generic.api.model.TestPO;
import com.netease.kaola.generic.api.service.TestService;
import com.netease.kaola.generic.provider.mapper.mysql.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hzwangqiqing
 * on 2017/8/10.
 */
@Service
public class TestServiceImpl implements TestService {
    @Resource
    private TestMapper testMapper;

    public TestPO getTestbyId(Long id) {
        return testMapper.getTestbyId(id);
    }

    public Integer insertTest(TestPO testPO) {
        return testMapper.insertTest(testPO);
    }

    public Integer deleteTestById(Long id) {
        return testMapper.deleteTestById(id);
    }

    public Integer updateTestBySelective(TestPO testPO) {
        return testMapper.updateTestBySelective(new TestPO());
    }
}
