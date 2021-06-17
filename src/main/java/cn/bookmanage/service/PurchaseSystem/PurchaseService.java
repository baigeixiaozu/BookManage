package cn.bookmanage.service.PurchaseSystem;

import cn.bookmanage.dao.PurchaseDao;
import cn.bookmanage.entity.Purchase;

import java.util.List;

public class PurchaseService {
    public List<Purchase> getAll(){
        return PurchaseDao.getAll();
    }
}
