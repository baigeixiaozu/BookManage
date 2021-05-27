package cn.bookmanage.dao;

import cn.bookmanage.entity.Book;
import cn.bookmanage.utils.JNDIUtils;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateDao {
    public static int insertByName(Book book) {
        Connection connection = null;
        try {
            connection = JNDIUtils.getConnection();
            if (connection != null) {
                String sql = "insert into bm_buy(user_id,book_id) values(?,?)";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, 3);
                ps.setInt(2, (int) book.getId());
                ps.execute();


                String sqlNum1 = "select book_count from bm_book where book_id=" + book.getId();
                String sqlNum2 = "select  count(*) as count from bm_buy where book_id= " + book.getId();
                PreparedStatement ps1 = connection.prepareStatement(sqlNum1);
                PreparedStatement ps2 = connection.prepareStatement(sqlNum2);
                ResultSet rs1 = ps1.executeQuery();
                ResultSet rs2 = ps2.executeQuery();

                rs1.next();
                rs2.next();
                int num1 = rs1.getInt("book_count");
                int num2 = (int)rs2.getInt("count");

                if (num1 < num2) {
                    System.out.println(num1+" "+num2);
                    ps.close();
                    ps1.close();
                    ps2.close();
                    return num1 - num2;
                }


            }

            connection.close();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

}
