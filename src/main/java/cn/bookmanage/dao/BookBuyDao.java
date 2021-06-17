package cn.bookmanage.dao;

import cn.bookmanage.entity.BookBuy;
import cn.bookmanage.utils.JNDIUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class BookBuyDao{
    public static List<BookBuy> getCount(){
        List<BookBuy> list=new LinkedList<>();
        try {
            Connection conn = JNDIUtils.getConnection();
            String statistics="select book_id,book_isbn,count(book_id) from bm_buy group by book_id";
            PreparedStatement ps=conn.prepareStatement(statistics);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                list.add(new BookBuy(){{
                    setBookId(rs.getInt(1));
                    setIsbn(rs.getString(2));
                    setCount(rs.getInt(3));
                }

                });
            }
            conn.close();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
