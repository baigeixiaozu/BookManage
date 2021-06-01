package cn.bookmanage.servlet.api.impl;

import cn.bookmanage.servlet.api.APIBaseServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class APIBaseServletImpl implements APIBaseServlet {
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected Map<String, String> params;

    public final void init(HttpServletRequest request, HttpServletResponse response, Map<String, String> params){
        this.request = request;
        this.response = response;
        this.params = params;
    }

}
