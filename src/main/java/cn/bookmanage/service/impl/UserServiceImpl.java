package cn.bookmanage.service.impl;

import cn.bookmanage.dao.UserDao;
import cn.bookmanage.entity.User;
import cn.bookmanage.service.UserService;

/**
 * @Author jiyec
 * @Date 2021/5/13 10:36
 * @Version 1.0
 **/
public class UserServiceImpl implements UserService {
    /**
     * 用户登录服务
     *
     * @param name 用户名
     * @param pass 密码
     * @return 成功-用户实体 | 失败-null
     */
    @Override
    public User login(String name, String pass) {
        return new UserDao().checkUser(name, pass);
    }


}
