package cn.bookmanage.servlet.api;

import java.io.IOException;

/**
 * @Author jiyec
 * @Date 2021/6/17 18:55
 * @Version 1.0
 **/
public interface IMsgServlet {
    void sendAction() throws IOException;
    void getAction() throws IOException;
}
