package cn.bookmanage.servlet.api;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author jiyec
 * @Date 2021/5/30 10:53
 * @Version 1.0
 **/
public interface IStoreServlet {
    void queryAllAction() throws IOException;
    void queryInAction() throws IOException;
    void queryOutAction() throws IOException;
}
