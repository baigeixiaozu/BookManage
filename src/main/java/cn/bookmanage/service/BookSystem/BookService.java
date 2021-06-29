package cn.bookmanage.service.BookSystem;

import cn.bookmanage.dao.BookDao;
import cn.bookmanage.entity.Book;

public class BookService {
    public static String BookIO(Book book){
        if(!book.getName().equals("")){
            return BookDao.BookIn(book);
        }
        else{
            return BookDao.BookOut(book);
        }
    }
    public static void quickOut(){
        BookDao.BookQuickOut();
    }
}
