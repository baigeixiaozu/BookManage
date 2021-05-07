package cn.bookmanage.servlet; /**
 * @Author jiyec
 * @Date 2021/5/7 10:26
 * @Version 1.0
 **/

import cn.bookmanage.dao.UserDao;
import cn.bookmanage.entity.User;
import cn.bookmanage.utils.JsonUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("LoginServlet");
        String username = request.getParameter("username");
        String userpass = request.getParameter("password");
        String redirect = request.getParameter("redirect");

        UserDao userDao = new UserDao();
        User user = userDao.checkUser(username, userpass);

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
