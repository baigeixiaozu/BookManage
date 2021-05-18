package cn.bookmanage.service.impl;

import cn.bookmanage.dao.StoreDao;
import cn.bookmanage.service.StoreService;

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

}
