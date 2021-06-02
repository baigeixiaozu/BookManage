package cn.bookmanage.servlet.api.impl;

import cn.bookmanage.entity.Book;
import cn.bookmanage.entity.StoreRecord;
import cn.bookmanage.entity.User;
import cn.bookmanage.exception.BaseException;
import cn.bookmanage.service.StoreService;
import cn.bookmanage.service.impl.StoreServiceImpl;
import cn.bookmanage.servlet.api.IStoreServlet;
import cn.bookmanage.utils.JsonUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author jiyec
 * @Date 2021/5/30 10:59
 * @Version 1.0
 **/
public class StoreServletImpl extends APIBaseServletImpl implements IStoreServlet {

    @Override
    public void queryAllAction() throws IOException {

        if (!checkUser()) {
            throw new BaseException(403, "权限不足");
        }

        String p = request.getParameter("page");
        String order = request.getParameter("order");
        if(order == null)order="1,0";
        if(order.length() != 3)throw new BaseException(401, "参数异常");

        int curPage = p == null || p.length() == 0 ? 1 : Integer.parseInt(p);
        int count = 10;
        StoreService ss = new StoreServiceImpl();
        Map<String, Object> data = ss.queryAll(curPage, count, order);
        List<Book> books = (List<Book>) data.get("books");
        int total = (int) data.get("total");
        int pageCnt = total / count + 1;

        Map<String, Object> ret = new HashMap<String, Object>() {{
            put("code", 2000);
            put("data", new HashMap<String, Object>(){{
                put("list", books);
                put("total", total);
                put("pageCnt", pageCnt);
                put("curPage", curPage);
            }});
        }};

        response.getWriter().print(JsonUtil.obj2String(ret));
    }

    @Override
    public void queryInAction() throws IOException {
        if (!checkUser()) {
            throw new BaseException(403, "权限不足");
        }

        String p = request.getParameter("page");
        String order = request.getParameter("order");
        if(order==null)order="1,0";
        int curPage = p==null||p.length()==0?1:Integer.parseInt(p);
        int count = 10;
        StoreService ss = new StoreServiceImpl();
        List<Object> data = ss.queryIn(curPage, count, order);
        List<StoreRecord> records = (List<StoreRecord>)data.get(1);
        int total = (int)data.get(0);
        int pageCnt = total/count + 1;

        Map<String, Object> ret = new HashMap<String, Object>() {{
            put("code", 2000);
            put("data", new HashMap<String, Object>(){{
                put("list", records);
                put("total", total);
                put("pageCnt", pageCnt);
                put("curPage", curPage);
            }});
        }};

        response.getWriter().print(JsonUtil.obj2String(ret));
    }

    @Override
    public void queryOutAction() throws IOException {
        if (!checkUser()) {
            throw new BaseException(403, "权限不足");
        }

        String p = request.getParameter("page");
        int curPage = p==null||p.length()==0?1:Integer.parseInt(p);
        int count = 10;
        StoreService ss = new StoreServiceImpl();
        List<Object> data = ss.queryOut(curPage, count);
        List<StoreRecord> records = (List<StoreRecord>)data.get(1);
        int total = (int)data.get(0);

        int pageCnt = total/count + 1;

        Map<String, Object> ret = new HashMap<String, Object>() {{
            put("code", 2000);
            put("data", new HashMap<String, Object>(){{
                put("list", records);
                put("total", total);
                put("pageCnt", pageCnt);
                put("curPage", curPage);
            }});
        }};

        response.getWriter().print(JsonUtil.obj2String(ret));
    }

    private boolean checkUser(){
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            return false;
        }
        return user.getLevel() == 10 || user.getLevel() == 6;
    }
}
