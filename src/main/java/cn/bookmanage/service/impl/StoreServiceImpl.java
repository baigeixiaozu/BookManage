package cn.bookmanage.service.impl;

import cn.bookmanage.dao.StoreDao;
import cn.bookmanage.entity.Book;
import cn.bookmanage.service.StoreService;

import java.util.List;
import java.util.Map;

/**
 * @Author jiyec
 * @Date 2021/5/17 20:16
 * @Version 1.0
 **/
public class StoreServiceImpl implements StoreService {
    public Map<String, Object> queryAll(int page, int count, String order){
        String[] orderArr = order.split(",");
        return StoreDao.queryAll(page, count, new int[]{Integer.parseInt(orderArr[0]), Integer.parseInt(orderArr[1])});
    }

    public List<Object> queryIn(int page, int count, String order, String[] time){
        String[] orderArr = order.split(",");
        return StoreDao.queryInOut(page, count, 0, new int[]{Integer.parseInt(orderArr[0]), Integer.parseInt(orderArr[1])}, time);
    }


    public List<Object> queryOut(int page, int count, String order, String[] time) {
        String[] orderArr = order.split(",");
        return StoreDao.queryInOut(page, count, 1, new int[]{Integer.parseInt(orderArr[0]), Integer.parseInt(orderArr[1])}, time);
    }

    public Book queryBook(int bookId){
        return StoreDao.queryBook(bookId);
    }
}
