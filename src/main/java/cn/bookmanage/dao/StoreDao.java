package cn.bookmanage.dao;

import cn.bookmanage.entity.Book;
import cn.bookmanage.utils.JNDIUtils;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author jiyec
 * @Date 2021/5/17 20:35
 * @Version 1.0
 **/
public class StoreDao {
    public static Map<String, Object> queryAll(int page, int count){
        Connection connection = null;

        List<Book> books = new LinkedList<>();
        int total = 0;
        try{
            connection = JNDIUtils.getConnection();

            String sql = "SELECT COUNT(1) FROM bm_book";
            ResultSet rs = JNDIUtils.executeQuery(connection, sql, null);
            while (rs.next()){
                total = rs.getInt(1);
            }
            rs.close();

            sql = "SELECT * FROM bm_book LIMIT ?,?";
            Integer[] p = new Integer[]{
                    (page - 1) * count,
                    count
            };
            rs = JNDIUtils.executeQuery(connection, sql, p);
            while (rs.next()){
                ResultSet finalRs = rs;
                books.add(new Book(){{
                    setId(finalRs.getLong("book_id"));
                    setName(finalRs.getString("book_name"));
                    setAuthor(finalRs.getString("book_author"));
                    setPublish(finalRs.getString("book_pub"));
                    setIsbn(finalRs.getString("book_isbn"));
                    setPrice(finalRs.getDouble("book_price"));
                    setCount(finalRs.getLong("book_count"));
                }});
            }
            rs.close();

        } catch (SQLException | NamingException throwables) {
            throwables.printStackTrace();
        } finally{
            if(null != connection) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        int finalTotal = total;
        return new HashMap<String, Object>(){{
            put("books", books);
            put("total", finalTotal);
        }};
    }
}
