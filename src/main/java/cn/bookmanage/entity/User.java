package cn.bookmanage.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户实体
 *
 * @Author jiyec
 * @Date 2021/5/7 9:46
 * @Version 1.0
 **/
@Getter
@Setter
public class User {
    // 用户id
    private int id;
    // 用户登录名
    private String login;
    // 用户昵称
    private String nickname;
    // 用户状态
    private int status;
    // 用户等级
    private int level;

    public User(){

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", nickname='" + nickname + '\'' +
                ", status=" + status +
                ", level=" + level +
                '}';
    }
}
