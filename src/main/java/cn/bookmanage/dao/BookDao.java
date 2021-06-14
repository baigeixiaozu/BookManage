package cn.bookmanage.dao;

import cn.bookmanage.entity.Book;
import cn.bookmanage.utils.JNDIUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookDao {
    public static void BookIn(Book book){
        try{
            Connection conn= JNDIUtils.getConnection();
            if( conn != null){
                String sql="insert into bm_book(book_isbn,book_author,book_pub,book_name,book_price,book_count) " +
                        "values(?,?,?,?,?,?) ON DUPLICATE KEY UPDATE book_price=values(?),book_count=values(?)";
                PreparedStatement ps=null;

                ps=conn.prepareStatement(sql);
                ps.setString(1,book.getIsbn());
                ps.setString(2,book.getAuthor());
                ps.setString(3,book.getPublish());
                ps.setString(4,book.getName());
                ps.setDouble(5,book.getPrice());
                ps.setDouble(6,book.getCount());
                ps.setDouble(7,book.getPrice());
                ps.setDouble(8,book.getCount());
                ps.execute();

                conn.close();
            }

        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static Book BookOut(Book book){
        return null;
    }
}
