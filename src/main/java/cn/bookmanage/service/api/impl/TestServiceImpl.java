package cn.bookmanage.service.api.impl;

import cn.bookmanage.service.api.ITestService;
import cn.bookmanage.service.impl.BaseServiceImpl;

import java.util.Map;

public class TestServiceImpl extends BaseServiceImpl implements ITestService {
    public TestServiceImpl(Map<String, Object> map) {
        super(map);
    }

    @Override
    public void testAction() {
        Map<String, Object> param = (Map<String, Object>) info.get("param");
        param.forEach((k, v)-> System.out.println(k));
        System.out.println("test");
    }

}
