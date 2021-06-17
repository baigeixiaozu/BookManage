package cn.bookmanage.service.MarketingSystem;

import cn.bookmanage.dao.UpdateDao;
import cn.bookmanage.entity.User;


public class InsertByIDService {
    public static void Insert(int id, User i) {
        UpdateDao.insertByID(id, i);
    }
}
