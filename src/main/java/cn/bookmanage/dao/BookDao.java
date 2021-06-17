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
            Connection conn= JNDIUtils.getConnection();
            ResultSet rs=null;
            if( conn != null){
                String search="select book_isbn from bm_book where book_isbn=?";
                String result=null;
                PreparedStatement ps=null;
                ps=conn.prepareStatement(search);
                ps.setString(1,book.getIsbn());
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
                String getBookID="select book_id from bm_book where book_isbn=?";
                int ID=0;
                ps=conn.prepareStatement(getBookID);
                ps.setString(1,book.getIsbn());
                rs=ps.executeQuery();
                while(rs.next()){
                    ID=rs.getInt("book_id");
                }

                //取出入库表里入库ID最大值
                String getInID="select MAX(in_id) from bm_in";
                int InID=0;
                ps=conn.prepareStatement(getInID);
                rs=ps.executeQuery();
                while(rs.next()){
                    InID=rs.getInt(1);
                }

                //获取时间，写入表中
                String log="insert into bm_in values(?,?,?,default )";
/*                java.util.Date date=new java.util.Date();
                long t=date.getTime();
                java.sql.Date time=new java.sql.Date(t);*/
                ps=conn.prepareStatement(log);
                ps.setInt(1,InID+1);
                ps.setInt(2,ID);
                ps.setDouble(3,book.getCount());
/*                ps.setDate(4,time);*/
                ps.execute();

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
            ResultSet rs=null;
            String update="update bm_book set book_count=book_count-? where book_isbn=?";
            PreparedStatement ps=conn.prepareStatement(update);
            ps.setDouble(1,book.getCount());
            ps.setString(2,book.getIsbn());
            ps.execute();

            //更新出库表
            String getBookID="select book_id from bm_book where book_isbn=?";
            int ID=0;
            ps=conn.prepareStatement(getBookID);
            ps.setString(1,book.getIsbn());
            rs=ps.executeQuery();
            while(rs.next()){
                ID=rs.getInt("book_id");
            }

            //取出出库表里入库ID最大值
            String getOutID="select max(out_id) from bm_out";
            int OutID=0;
            ps=conn.prepareStatement(getOutID);
            rs=ps.executeQuery();
            while(rs.next()){
                OutID=rs.getInt(1);
            }

            //获取时间，写入表中
            String log="insert into bm_out values(?,?,?,default )";
/*            java.util.Date date=new java.util.Date();
            long t=date.getTime();
            java.sql.Date time=new java.sql.Date(t);*/
            ps=conn.prepareStatement(log);
            ps.setInt(1,OutID+1);
            ps.setInt(2,ID);
            ps.setDouble(3,book.getCount());
/*            ps.setDate(4,time);*/
            ps.execute();

            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
