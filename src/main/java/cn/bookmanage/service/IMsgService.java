package cn.bookmanage.service;

import cn.bookmanage.entity.Message;

import java.util.List;

/**
 * @Author jiyec
 * @Date 2021/6/17 22:07
 * @Version 1.0
 **/
public interface IMsgService {
    int sendMsg(int senderId, int receiverLevel, String msg);
    List<Message> get(int receiver);
}
