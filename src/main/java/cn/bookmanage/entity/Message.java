package cn.bookmanage.entity;

import lombok.Data;

/**
 * @Author jiyec
 * @Date 2021/6/17 19:34
 * @Version 1.0
 **/
@Data
public class Message {
    private int id;
    private String content;
    private String time;
    private int sender;
    private int receiver;
    private int reader_state;
}
