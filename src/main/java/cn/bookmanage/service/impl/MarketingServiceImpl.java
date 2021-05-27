package cn.bookmanage.service.impl;

import cn.bookmanage.dao.MarketingDao;
import cn.bookmanage.dao.StoreDao;

import java.util.List;

public class MarketingServiceImpl {
    public List<Object> queryAuthor(int page, int count, String author){
        return MarketingDao.queryAuthor(page, count, author);
    }
    public List<Object> queryName(int page, int count, String name){
        return MarketingDao.queryName(page, count, name);
    }
    public List<Object> queryISBN(int page, int count, String ISBN){
        return MarketingDao.queryISBN(page, count, ISBN);
    }
}
