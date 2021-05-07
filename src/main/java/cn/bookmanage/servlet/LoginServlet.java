package cn.bookmanage.servlet; /**
 * @Author jiyec
 * @Date 2021/5/7 10:26
 * @Version 1.0
 **/

import cn.bookmanage.dao.UserDao;
import cn.bookmanage.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

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
        if(null != user){
            // 存在用户
            request.getSession().setAttribute("user", user);
            if(null != redirect)
                response.sendRedirect(redirect);
            else
                System.out.println("登录成功");
            return;
        }
        request.setAttribute("msg", "登录失败！用户名或密码错误！");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
