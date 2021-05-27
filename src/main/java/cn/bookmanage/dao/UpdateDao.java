package cn.bookmanage.dao;

import cn.bookmanage.entity.Book;
import cn.bookmanage.utils.JNDIUtils;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateDao {
    public static int insertByName(Book book,int num) {
        Connection connection = null;
        try {
            connection = JNDIUtils.getConnection();
            if (connection != null&&num!=0) {
                String sql = "insert into bm_buy(user_id,book_id,book_num) values(?,?,?)";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1,6);
                ps.setInt(2,  (int) book.getId());
                ps.setInt(3,num);
                ps.execute();


                sql="select book_count from bm_book where book_id="+book.getId();
                ResultSet rs = ps.executeQuery(sql);
                if(rs.next()){
                    int n=rs.getInt("book_count");
                   if(num<n){
                    return n-num;
                   } else{
                       return num-n;
                   }
                }
                ps.close();
                connection.close();
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

}
