package cn.bookmanage.dao;

import cn.bookmanage.entity.Book;
import cn.bookmanage.entity.Order;
import cn.bookmanage.entity.User;
import cn.bookmanage.utils.JNDIUtils;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class MarketingDao {
    public static List<Object> queryAuthor(int page, int count, String author) {
        Connection conn = null;

        List<Book> books = new LinkedList<>();
        int total = 0;
        try {
            conn = JNDIUtils.getConnection();
            PreparedStatement ps = null;
            ps = conn.prepareStatement("SELECT COUNT(1) FROM bm_book WHERE book_author like ?");
            ps.setString(1,"%" +author + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                total=rs.getInt(1);
            }
            rs.close();

            ps = null;
            ps = conn.prepareStatement("SELECT * FROM bm_book WHERE book_author like ? LIMIT ?,?");
            ps.setString(1,"%" +author + "%");
            ps.setInt(2,(page - 1) * count);
            ps.setInt(3, count);

            rs = ps.executeQuery();
            while (rs.next()) {
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
            if(null != conn) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        int finalTotal = total;
        return new LinkedList<Object>() {{
            add(finalTotal);
            add(books);
        }};
    }

    public static List<Object> queryName(int page, int count, String name) {
        Connection conn = null;

        List<Book> books = new LinkedList<>();
        int total = 0;
        try {
            conn = JNDIUtils.getConnection();
            PreparedStatement ps = null;
            ps = conn.prepareStatement("SELECT COUNT(1) FROM bm_book WHERE book_name like ?");
            ps.setString(1,"%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                total=rs.getInt(1);
            }
            rs.close();

            ps = null;
            ps = conn.prepareStatement("SELECT * FROM bm_book WHERE book_name like ? LIMIT ?,?");
            ps.setString(1,"%" +name+ "%");
            ps.setInt(2,(page - 1) * count);
            ps.setInt(3, count);

            rs = ps.executeQuery();
            while (rs.next()) {
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
            if(null != conn) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        int finalTotal = total;
        return new LinkedList<Object>() {{
            add(finalTotal);
            add(books);
        }};
    }

    public static List<Object> queryISBN(int page, int count, String ISBN) {
        Connection conn = null;

        List<Book> books = new LinkedList<>();
        int total = 0;
        try {
            conn = JNDIUtils.getConnection();
            PreparedStatement ps = null;
            ps = conn.prepareStatement("SELECT COUNT(1) FROM bm_book WHERE book_isbn like ?");
            ps.setString(1,"%" + ISBN + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                total=rs.getInt(1);
            }
            rs.close();

            ps = null;
            ps = conn.prepareStatement("SELECT * FROM bm_book WHERE book_isbn like ? LIMIT ?,?");
            ps.setString(1,"%" +ISBN+ "%");
            ps.setInt(2,(page - 1) * count);
            ps.setInt(3, count);

            rs = ps.executeQuery();
            while (rs.next()) {
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
            if(null != conn) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        int finalTotal = total;
        return new LinkedList<Object>() {{
            add(finalTotal);
            add(books);
        }};
    }

    public static List<Object> queryOrder(int page, int count, User user) {
        Connection conn = null;

        List<Order> orders = new LinkedList<>();
        int total = 0;
        try {
            conn = JNDIUtils.getConnection();
            PreparedStatement ps = null;
            ps = conn.prepareStatement("select COUNT(1) from bm_buy join bm_book on bm_buy.book_id = bm_book.book_id where user_id = ?");
            ps.setInt(1,user.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                total=rs.getInt(1);
            }
            rs.close();

            ps = null;
            ps = conn.prepareStatement("select buy_id, bm_book.* from bm_buy join bm_book on bm_buy.book_id = bm_book.book_id where user_id = ? LIMIT ?,?");
            ps.setInt(1,user.getId());
            ps.setInt(2,(page - 1) * count);
            ps.setInt(3, count);

            rs = ps.executeQuery();
            while (rs.next()) {
                ResultSet finalRs = rs;
                orders.add(new Order(){{
                    setOrder_id(finalRs.getLong("buy_id"));
                    setId(finalRs.getLong("book_id"));
                    setName(finalRs.getString("book_name"));
                    setAuthor(finalRs.getString("book_author"));
                    setPublish(finalRs.getString("book_pub"));
                    setIsbn(finalRs.getString("book_isbn"));
                    setPrice(finalRs.getDouble("book_price"));
                }});
            }
            rs.close();

        } catch (SQLException | NamingException throwables) {
            throwables.printStackTrace();
        } finally{
            if(null != conn) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        int finalTotal = total;
        return new LinkedList<Object>() {{
            add(finalTotal);
            add(orders);
        }};
    }
}
