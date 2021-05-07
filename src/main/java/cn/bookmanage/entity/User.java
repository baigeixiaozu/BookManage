package cn.bookmanage.entity;

/**
 * 用户实体
 *
 * @Author jiyec
 * @Date 2021/5/7 9:46
 * @Version 1.0
 **/
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

    public int getLevel() {
        return level;
    }

    public void setLevel(String level) {
        if(level == null)
            this.level = 0;
        this.level = Integer.parseInt(level);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
