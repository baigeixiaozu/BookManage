package cn.bookmanage.dao;

import cn.bookmanage.entity.Book;
import cn.bookmanage.utils.JNDIUtils;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class BookDao {
    public static void BookIn(Book book){
        try{
            System.out.println(book.getName());
            Connection conn= JNDIUtils.getConnection();
            ResultSet rs=null;
            if( conn != null){
                String search="select book_isbn from bm_book where book_name=?";
                String result=null;
                PreparedStatement ps=null;
                ps=conn.prepareStatement(search);
                ps.setString(1,book.getName());
                rs=ps.executeQuery();
                while(rs.next()){
                    result=rs.getString("book_isbn");
                }

                //如果库里没有相同ISBN码的书籍，则新建信息
                if(result==null||!result.equals(book.getIsbn())){
                    String insert="insert into bm_book(book_name,book_author,book_pub,book_isbn,book_price,book_count) " +
                            "values(?,?,?,?,?,?)";
                    ps=conn.prepareStatement(insert);
                    ps.setString(1,book.getName());
                    ps.setString(2,book.getAuthor());
                    ps.setString(3,book.getPublish());
                    ps.setString(4,book.getIsbn());
                    ps.setDouble(5,book.getPrice());
                    ps.setDouble(6,book.getCount());
                    ps.execute();
                }
                //如果有相同ISBN码，则更新库存量
                else{
                    String update="update bm_book set book_count=book_count+? where book_isbn=?";
                    ps=conn.prepareStatement(update);
                    ps.setDouble(1,book.getCount());
                    ps.setString(2,book.getIsbn());
                    ps.execute();
                }

                //写入 入库表
                //获取书籍ID
                String getID="select book_id from bm_book where book_isbn=?";
                int ID=0;
                ps=conn.prepareStatement(getID);
                ps.setString(1,book.getIsbn());
                rs=ps.executeQuery();
                while(rs.next()){
                    ID=rs.getInt("book_id");
                }

                //获取时间，写入表中
                String log="insert into bm_in values(?,?,?)";
                java.util.Date date=new java.util.Date();
                long t=date.getTime();
                java.sql.Date time=new java.sql.Date(t);
                ps=conn.prepareStatement(log);
                ps.setInt(1,ID);
                ps.setDouble(2,book.getCount());
                ps.setDate(3,time);

                conn.close();
            }

        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void BookOut(Book book){
        try{
            //更新书库
            Connection conn=JNDIUtils.getConnection();
            String update="update bm_book set book_count=book_count-? where book_isbn=?";
            PreparedStatement ps=conn.prepareStatement(update);
            ps.setDouble(1,book.getCount());
            ps.setString(2,book.getIsbn());
            ps.execute();

            //更新出库表


            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
