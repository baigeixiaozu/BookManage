package cn.bookmanage.service.api.impl;

import cn.bookmanage.dao.UserDao;
import cn.bookmanage.entity.User;
import cn.bookmanage.service.api.IUserService;
import cn.bookmanage.service.impl.APIServiceImpl;
import cn.bookmanage.utils.JsonUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author jiyec
 * @Date 2021/5/13 10:54
 * @Version 1.0
 **/
public class UserServiceImpl extends APIServiceImpl implements IUserService {
    @Override
    public void loginAction() throws IOException {
        String username = request.getParameter("username");
        String userpass = request.getParameter("password");
        // 跳转地址
        String redirect = request.getParameter("redirect");

        User user = new UserDao().checkUser(username, userpass);

        Map<String, Object> ret = new HashMap<String, Object>(){{
            put("code", 2000);
        }};

        if(null != user){
            // 存在用户
            request.getSession().setAttribute("user", user);
            if(null != redirect)
                ret.put("msg", redirect);
            else
                ret.put("msg", "user/info.jsp");
        }else{
            ret.put("code", 20403);
            ret.put("msg", "登录失败！用户名或密码错误！");
        }

        response.setContentType("application/json");
        response.getWriter().print(JsonUtil.obj2String(ret));
    }
}
