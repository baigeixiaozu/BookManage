package cn.bookmanage.service.impl;

import cn.bookmanage.service.APIService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class APIServiceImpl implements APIService {
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected Map<String, String> params;

    public final void init(HttpServletRequest request, HttpServletResponse response, Map<String, String> params){
        this.request = request;
        this.response = response;
        this.params = params;
    }

}
