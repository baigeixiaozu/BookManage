package cn.bookmanage.service;

import java.util.Map;

/**
 * @Author jiyec
 * @Date 2021/5/17 20:16
 * @Version 1.0
 **/
public interface StoreService {
    Map<String, Object> queryAll(int page, int count);
}
