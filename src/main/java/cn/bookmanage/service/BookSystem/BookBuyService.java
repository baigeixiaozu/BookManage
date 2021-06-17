package cn.bookmanage.service.BookSystem;

import cn.bookmanage.dao.BookBuyDao;
import cn.bookmanage.entity.BookBuy;

import java.util.LinkedList;
import java.util.List;

public class BookBuyService {
    public List<BookBuy> getCount(){
        return BookBuyDao.getCount();
    }
}
