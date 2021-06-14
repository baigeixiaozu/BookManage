package cn.bookmanage.service.BookSystem;

import cn.bookmanage.dao.BookDao;
import cn.bookmanage.entity.Book;

public class BookService {
    public static void BookIO(Book book){
        if(book.getAuthor()!=null){
            BookDao.BookIn(book);
        }
        else{
            book=BookDao.BookOut(book);
        }
    }
}
