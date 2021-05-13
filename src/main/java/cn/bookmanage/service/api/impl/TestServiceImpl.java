package cn.bookmanage.service.api.impl;

import cn.bookmanage.service.api.ITestService;
import cn.bookmanage.service.impl.APIServiceImpl;
import cn.bookmanage.utils.JsonUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestServiceImpl extends APIServiceImpl implements ITestService {

    /**
     * 方法中不能含参，否则无法被调用
     */
    @Override
    public void testAction() {
        params.forEach((k, v)-> System.out.println(k + "-" + v));
        System.out.println("test");

        Map<String, Object> ret = new HashMap<>();
        ret.put("code", 2000);
        ret.put("msg", "测试执行成功！");
        try {
            response.getWriter().print(JsonUtil.obj2String(ret));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
