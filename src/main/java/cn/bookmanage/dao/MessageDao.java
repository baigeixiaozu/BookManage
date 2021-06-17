package cn.bookmanage.dao;

import cn.bookmanage.entity.Book;
import cn.bookmanage.entity.User;
import cn.bookmanage.entity.info;
import cn.bookmanage.service.purchasing.PurchasingServices;
import cn.bookmanage.utils.JNDIUtils;

import javax.naming.NamingException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class MessageDao {
    public static ArrayList<info> fetch() {
        Connection connection = null;
        ArrayList<info> samp=new ArrayList<info>();
        info sample=null;
        String sql = "select * from bm_purchase where need>0 ";
        ResultSet rs=null;
        try {
            connection = JNDIUtils.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                rs=ps.executeQuery();
                int i=0;
                while(rs.next()){
                    sample=new info();
                    Long id=rs.getLong(1);
                    String Name=rs.getString(2);
                    String Isbn=rs.getString(3);
                    Double price=rs.getDouble(4);
                    int Need=rs.getInt(5);
                    String content="需采购编号为"+id+"名字为"+Name+"价格为"+price+"Isbn为"+Isbn+"的书"+Need+"本";
                    sample.setContent(content);
                    sample.setInfo_id(id);
                    sample.setRead_status(0);
                    sample.setSender("超级管理员");
                    sample.setReceiver("采购员");
                    samp.add(sample);
                }
                ps.close();
                connection.close();

        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return samp;
    }
    public static ArrayList<info> fetch_i(int level) {
        Connection connection = null;
        ArrayList<info> samp=new ArrayList<info>();
        info sample=null;
        String sql = null;
        ResultSet rs=null;
        System.out.print(level);
        try {
            if(level==10){
               sql = "select * from bm_info where read_state=0 ";}
            else if(level==6){
                sql = "select * from bm_info where read_state=0 and receiver='采购员'";
            }
            else{
               sql = "select * from bm_info where read_state=0 and receiver='订书员'";
            }
            connection = JNDIUtils.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            rs=ps.executeQuery();
            int i=0;
            while(rs.next()){
                sample=new info();
                Long id=rs.getLong(1);
                String content=rs.getString(2);
                Date time=rs.getDate(3);
                String receiver=rs.getString(4);
                String sender= rs.getNString(5);
                int read_state=rs.getInt(6);
                sample.setRead_status(read_state);
                sample.setInfo_id(id);
                sample.setRead_status(0);
                sample.setSender(sender);
                sample.setReceiver(receiver);
                sample.setContent(content);
                sample.setTime(time);
                samp.add(sample);
            }
            ps.close();
            connection.close();

        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return samp;
    }
    public static void store(ArrayList<info> sample)  {//将需要购买的消息存入数据库
        Connection connection = null;
       String sql = "insert into bm_info(info_id,content,time,receiver,sender,read_state) values(?,?,?,?,?,?)";
        try {
            int i=0;
            for(i=0;i<sample.size();i++){
                connection = JNDIUtils.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                Long t=(sample.get(i).getInfo_id());
                int tem=Integer.valueOf(t.toString());
                ps.setInt(1,tem);
                String temp=sample.get(i).getContent();
                ps.setString(2,temp);
                Date date=new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                PreparedStatement pst = connection.prepareStatement(sql);
                ps.setDate(3, sqlDate);
                ps.setString(4,sample.get(i).getReceiver());
                ps.setString(5,sample.get(i).getSender());
                ps.setInt(6,0);
                ps.execute();
                ps.close();
            }
            connection.close();

        } catch (SQLException | NamingException throwables) {
            throwables.printStackTrace();
        }
    }
    public static int[] purchase(int id, BigInteger num){
        Connection connection = null;
        String sql=null;

         sql = "select buy_id from bm_buy where buy_verify_status=0 and book_id="+id+" order by book_id limit "+num;

        ResultSet rs=null;
        int[] a=new int[num.intValue()];
        try {
            connection = JNDIUtils.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            rs=ps.executeQuery();
            int i=0;

            while(rs.next()){
                a[i++]=rs.getInt(1);
            }
            ps.close();
            connection.close();

        } catch (SQLException | NamingException throwables) {
            throwables.printStackTrace();
        }
        int t=0;
        for(int i=0;i<a.length;i++) {
            t=a[i];
            sql = "Update bm_buy set buy_verify_status=2  where buy_id=" + t;
           try{
               connection=JNDIUtils.getConnection();
               PreparedStatement ps = connection.prepareStatement(sql);
               ps.execute();
                connection.close();
           }
           catch (SQLException | NamingException throwables) {
               throwables.printStackTrace();
           }
        }
        if(a.length!=0)
        return a;
        else
            return null;
    }
    public static Long search_id() {
        Connection connection = null;
        Long temp=0L;
        String sql = "select * from bm_info group by info_id order by info_id  DESC limit 1" ;
        ResultSet rs=null;
        try {
            connection = JNDIUtils.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            rs=ps.executeQuery();
            rs.next();
            temp=rs.getLong(1);
            ps.close();
            connection.close();

        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return temp;
    }

    /**
     * 发送一条消息
     * @param sender    发送者id
     * @param receiver  接收者id
     * @param msg       消息内容
     * @return          自增id - [int]
     */
    public static int sendMsg(int sender, int receiver, String msg){
        String sql = "INSERT INTO bm_msg(`sender`, `receiver`, `content`) VALUES(?, ?, ?)";
        Connection connection = null;
        try {
            connection = JNDIUtils.getConnection();
            Object[] p = {sender, receiver, msg};
            return JNDIUtils.executeUpdate(connection, sql, p);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return 0;
    }

}

