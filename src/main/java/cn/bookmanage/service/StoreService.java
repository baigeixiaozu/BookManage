package cn.bookmanage.service;

import cn.bookmanage.entity.StoreRecord;

import java.util.List;
import java.util.Map;

/**
 * @Author jiyec
 * @Date 2021/5/17 20:16
 * @Version 1.0
 **/
public interface StoreService {
    Map<String, Object> queryAll(int page, int count, String order);
    List<Object> queryIn(int page, int count, String order, String[] time);
    List<Object> queryOut(int page, int count, String order, String[] time);
}
