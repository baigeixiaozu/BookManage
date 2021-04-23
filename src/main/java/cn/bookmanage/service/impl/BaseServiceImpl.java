package cn.bookmanage.service.impl;

import cn.bookmanage.service.IBaseService;

import java.util.Map;

public class BaseServiceImpl implements IBaseService {
    protected Map<String, Object> info;
    public BaseServiceImpl() {
    }

    public BaseServiceImpl(Map<String, Object> map) {
        System.out.println("BaseServiceImpl -- Constructor");
        info = map;
    }

}
