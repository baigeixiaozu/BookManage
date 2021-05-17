package cn.bookmanage.service;

import cn.bookmanage.entity.User;

/**
 * @Author jiyec
 * @Date 2021/5/13 10:33
 * @Version 1.0
 **/
public interface UserService {
    User login(String name, String pass);
}
