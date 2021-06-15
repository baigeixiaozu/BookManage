package cn.bookmanage.service.BookSystem;

import cn.bookmanage.dao.BookDao;
import cn.bookmanage.entity.Book;

public class BookService {
    public static void BookIO(Book book){
        if(!book.getAuthor().equals("")){
            BookDao.BookIn(book);
        }
        else{
            BookDao.BookOut(book);
        }
    }
}
