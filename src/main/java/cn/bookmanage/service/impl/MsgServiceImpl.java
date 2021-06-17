package cn.bookmanage.service.impl;

import cn.bookmanage.dao.MessageDao;
import cn.bookmanage.entity.Message;
import cn.bookmanage.service.IMsgService;

import java.util.List;

/**
 * @Author jiyec
 * @Date 2021/6/17 22:08
 * @Version 1.0
 **/
public class MsgServiceImpl implements IMsgService {

    /**
     * 发送一条消息
     * @param senderId    发送者ID
     * @param receiverLevel  接收者Level
     * @param msg       消息内容
     * @return          自增id - [int]
     */
    public int sendMsg(int senderId, int receiverLevel, String msg){
        return MessageDao.sendMsg(senderId, receiverLevel, msg);
    }

    /**
     * 获取指定等级的消息
     * @param receiver 指定等级
     * @return List<Message>
     */
    public List<Message> get(int receiver){
        return MessageDao.get(receiver);
    }
}
