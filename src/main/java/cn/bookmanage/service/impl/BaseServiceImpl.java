package cn.bookmanage.service.impl;

import cn.bookmanage.service.IBaseService;

import java.util.Map;

public class BaseServiceImpl implements IBaseService {
    protected Map<String, Object> info;

    /**
     * 含Map参数的构造方法用于存储请求时的一些数据，如request，response，params
     * 必须继承本构造方法；否则，子类下所有方法都无法被调用
     * @param map
     */
    public BaseServiceImpl(Map<String, Object> map) {
        System.out.println("BaseServiceImpl -- Constructor");
        info = map;
    }

}
