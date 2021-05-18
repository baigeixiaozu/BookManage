package cn.bookmanage.service.impl;

import cn.bookmanage.dao.StoreDao;
import cn.bookmanage.service.StoreService;

import java.util.List;
import java.util.Map;

/**
 * @Author jiyec
 * @Date 2021/5/17 20:16
 * @Version 1.0
 **/
public class StoreServiceImpl implements StoreService {
    public Map<String, Object> queryAll(int page, int count){
        return StoreDao.queryAll(page, count);
    }

    public List<Object> queryIn(int page, int count){
        return StoreDao.queryInOut(page, count, 0);
    }

    @Override
    public List<Object> queryOut(int page, int count) {
        return StoreDao.queryInOut(page, count, 1);
    }

}
