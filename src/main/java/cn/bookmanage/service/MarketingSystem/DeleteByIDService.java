package cn.bookmanage.service.MarketingSystem;

import cn.bookmanage.dao.UpdateDao;
import cn.bookmanage.entity.User;

public class DeleteByIDService {
    public static void Delete(int id, User user) {
        UpdateDao.deleteByID(id, user);
    }
}
