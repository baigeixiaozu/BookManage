package cn.bookmanage.service.api.impl;

import cn.bookmanage.service.api.ITestService;
import cn.bookmanage.service.impl.BaseServiceImpl;
import cn.bookmanage.utils.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestServiceImpl extends BaseServiceImpl implements ITestService {

    /**
     * 必须含有此构造方法，这个含Map参数的构造方法 用于存储请求时的一些数据，如request，response，params
     * 否则，本类下所有方法都无法被调用
     *
     * @param map
     */
    public TestServiceImpl(Map<String, Object> map) {
        super(map);
    }

    /**
     * 方法中不能含参，否则无法被调用
     */
    @Override
    public void testAction() {
        Map<String, Object> param = (Map<String, Object>) info.get("param");
        param.forEach((k, v)-> System.out.println(k));
        System.out.println("test");

        Map<String, Object> ret = new HashMap<>();
        ret.put("code", 2000);
        ret.put("msg", "测试执行成功！");
        HttpServletResponse response = (HttpServletResponse)info.get("response");
        try {
            response.getWriter().print(JsonUtil.obj2String(ret));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
