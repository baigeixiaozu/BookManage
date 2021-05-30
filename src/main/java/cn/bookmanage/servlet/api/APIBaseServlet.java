package cn.bookmanage.servlet.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface APIBaseServlet {
    void init(HttpServletRequest request, HttpServletResponse response, Map<String, String> params);
}
