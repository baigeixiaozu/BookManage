package cn.bookmanage.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface IBaseService {
    void init(HttpServletRequest request, HttpServletResponse response, Map<String, String> params);
}
