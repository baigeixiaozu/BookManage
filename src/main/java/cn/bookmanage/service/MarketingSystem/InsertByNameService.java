package cn.bookmanage.service.MarketingSystem;

import cn.bookmanage.dao.UpdateDao;
import cn.bookmanage.entity.Book;

public class InsertByNameService {


    public static int[] Insert(String[] bookName, String[] bookCount) {
        int i = 0;
        int num;
        int[] lack = new int[6];
        for (String name : bookName) {
            num = Integer.parseInt(bookCount[i]);
            Book book = new Book();
            book.setName(name);
            if (name.contains("JSP")) {
                book.setId(1);
            } else if (name.startsWith("计算机")) {
                book.setId(2);
            } else if (name.startsWith("软件工程")) {
                book.setId(3);
            } else if (name.startsWith("web前端")) {
                book.setId(4);
            } else if (name.startsWith("新目标")) {
                book.setId(5);
            } else if (name.startsWith("概率论")) {
                book.setId(6);
            }

            lack[i] = UpdateDao.insertByName(book, num);
            i++;
        }
        return lack;
    }
}
