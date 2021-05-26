package cn.bookmanage.dao;

import cn.bookmanage.entity.Book;
import cn.bookmanage.utils.JNDIUtils;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateDao {
    public static void insertByName(Book book,int num) {
        Connection connection = null;
        try {
            connection = JNDIUtils.getConnection();
            if (connection != null) {
                String sql = "insert into bm_buy(user_id,book_id,book_num) values(?,?,?)";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, (int) book.getId());
                ps.setInt(2, 6);
                ps.setInt(3,num);
                ps.execute();
                ps.close();
                connection.close();
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
