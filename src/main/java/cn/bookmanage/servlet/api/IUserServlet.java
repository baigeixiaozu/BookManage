package cn.bookmanage.servlet.api;

import java.io.IOException;

/**
 * @Author jiyec
 * @Date 2021/5/13 10:53
 * @Version 1.0
 **/
public interface IUserServlet {
    void loginAction() throws IOException;
    void logoutAction() throws IOException;
}
