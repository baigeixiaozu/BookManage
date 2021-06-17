package cn.bookmanage.dao;

import cn.bookmanage.entity.Message;
import cn.bookmanage.utils.JNDIUtils;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class MessageDao {

    /**
     * 发送一条消息
     * @param sender    发送者ID
     * @param receiver  接收者Level
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

    /**
     * 获取指定等级的消息
     * @param receiver 指定等级
     * @return List<Message>
     */
    public static List<Message> get(int receiver){
        List<Message> list = new LinkedList<>();
        String sql = "SELECT msg_id,content,time,receiver,read_state,user_nickname AS senderName,ID AS senderId FROM bm_msg LEFT JOIN  bm_user ON sender=ID  WHERE receiver=?";
        Connection connection = null;
        try{
            connection = JNDIUtils.getConnection();
            Object[] p ={receiver};

            ResultSet rs = JNDIUtils.executeQuery(connection, sql, p);
            while (rs.next()){
                list.add(new Message(){{
                    setId(rs.getInt("msg_id"));
                    setContent(rs.getString("content"));
                    setTime(rs.getString("time"));
                    setSenderId(rs.getInt("senderId"));
                    setSenderName(rs.getString("senderName"));
                    setReceiver(rs.getInt("receiver"));
                    setReader_state(rs.getInt("read_state"));
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

