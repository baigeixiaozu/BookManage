package cn.bookmanage.dao;

import cn.bookmanage.entity.Book;
import cn.bookmanage.entity.StoreRecord;
import cn.bookmanage.utils.JNDIUtils;

import javax.naming.NamingException;
import java.sql.Connection;
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
    public static Map<String, Object> queryAll(int page, int count, int[] orderInfo){
        Connection connection = null;

        List<Book> books = new LinkedList<>();
        int total = 0;
        String[] orderType = {"ASC", "DESC"};

        try{
            connection = JNDIUtils.getConnection();

            String sql = "SELECT COUNT(1) FROM bm_book";
            ResultSet rs = JNDIUtils.executeQuery(connection, sql, null);
            while (rs.next()){
                total = rs.getInt(1);
            }
            rs.close();

            sql = "SELECT * FROM bm_book ORDER BY " + orderInfo[0] + " " +  orderType[orderInfo[1]] + " LIMIT ?,?";
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

    /**
     * ???????????????
     * @param page  ??????
     * @param count ????????????
     * @param type  ?????? [0??????|1??????]
     * @param orderInfo ???????????? {????????????, ????????????}
     * @return List -- [total, recordList]
     */
    public static List<Object> queryInOut(int page, int count, int type, int[] orderInfo, String[] time){
        Connection connection = null;

        String[] table = {"in", "out"};
        String[] orderType = {"ASC", "DESC"};

        List<StoreRecord> books = new LinkedList<>();
        int total = 0;
        try{
            connection = JNDIUtils.getConnection();

            Object[] p = null;

            // ?????????
            String sql = "SELECT COUNT(1) FROM bm_" + table[type];
            if(time!=null){
                p = new Object[]{time[0], time[1]};
                sql += " WHERE " + table[type] + "_time>=? AND " + table[type] + "_time<=? ";
            }

            ResultSet rs = JNDIUtils.executeQuery(connection, sql, p);
            while (rs.next()){
                total = rs.getInt(1);
            }
            rs.close();

            // ?????????????????????????????????????????????????????????????????????????????????
            sql = "SELECT " + table[type] + "_id, book_name, "  + table[type] + "_count, "  + table[type] + "_time" +
                    " FROM `bm_" + table[type] + "` LEFT JOIN bm_book ON bm_" + table[type] + ".book_id=bm_book.book_id ";
            if(time!=null) {
                sql += "WHERE " + table[type] + "_time>=? AND " + table[type] + "_time<=? ";
            }
            sql += "ORDER BY " + orderInfo[0] + " " + orderType[orderInfo[1]] +
                    " LIMIT ?,?";
            if (time != null) {
                p = new Object[]{
                        time[0],
                        time[1],
                        (page - 1) * count,
                        count
                };
            }else {
                p = new Object[]{
                        (page - 1) * count,
                        count
                };
            }
            rs = JNDIUtils.executeQuery(connection, sql, p);
            while (rs.next()){
                ResultSet finalRs = rs;
                books.add(new StoreRecord(){{
                    setId(finalRs.getLong(table[type] + "_id"));
                    setName(finalRs.getString("book_name"));
                    setCount(finalRs.getLong(table[type] + "_count"));
                    setTime(finalRs.getString(table[type] + "_time"));
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
        return new LinkedList<Object>(){{
            add(finalTotal);
            add(books);
        }};
    }

    public static Book queryBook(int bookId){
        Connection connection;
        Book book = null;
        try{
            connection = JNDIUtils.getConnection();
            String sql = "SELECT * FROM bm_book WHERE book_id=?";
            Object[] p = {bookId};
            ResultSet resultSet = JNDIUtils.executeQuery(connection, sql, p);
            if (resultSet.next()){
                book = new Book(){{
                    setId(resultSet.getInt("book_id"));
                    setName(resultSet.getString("book_name"));
                    setAuthor(resultSet.getString("book_author"));
                    setPublish(resultSet.getString("book_pub"));
                    setIsbn(resultSet.getString("book_isbn"));
                    setPrice(resultSet.getDouble("book_price"));
                }};
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return book;
    }

    public static boolean updateBook(Book book){
        Connection connection;
        try{
            connection = JNDIUtils.getConnection();
            String sql = "UPDATE bm_book SET book_name=?, book_author=?,book_pub=?,book_isbn=?,book_price=? WHERE book_id=?";
            Object[] p = {
                    book.getName(),
                    book.getAuthor(),
                    book.getPublish(),
                    book.getIsbn(),
                    book.getPrice(),
                    book.getId()
            };
            int cnt = JNDIUtils.executeUpdate(connection, sql, p);
            return cnt==1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
