package cn.bookmanage.dao;

import cn.bookmanage.entity.Purchase;
import cn.bookmanage.utils.JNDIUtils;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author jiyec
 * @Date 2021/6/17 22:18
 * @Version 1.0
 **/
public class PurchaseDao {
    public static List<Purchase> getAll(){
        List<Purchase> list = new LinkedList<>();
        try{
            Connection connection = JNDIUtils.getConnection();
            String sql = "SELECT * FROM bm_purchase";
            ResultSet rs = JNDIUtils.executeQuery(connection, sql, new Object[]{});
            while (rs.next()){
                list.add(new Purchase(){{
                    setBookId(rs.getInt("book_id"));
                    setName(rs.getString("book_name"));
                    setIsbn(rs.getString("book_isbn"));
                    setPrice(rs.getDouble("book_price"));
                    setNeed(rs.getInt("need"));
                }});
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return list;
    }
}
