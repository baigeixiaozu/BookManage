package cn.bookmanage.servlet.api.impl;

import cn.bookmanage.dao.MessageDao;
import cn.bookmanage.entity.Message;
import cn.bookmanage.entity.User;
import cn.bookmanage.exception.BaseException;
import cn.bookmanage.servlet.api.IMsgServlet;
import cn.bookmanage.utils.JsonUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author jiyec
 * @Date 2021/6/17 18:56
 * @Version 1.0
 **/
public class MsgServletImpl extends APIBaseServletImpl implements IMsgServlet{
    public void sendAction() throws IOException {
        User user = (User)request.getSession().getAttribute("user");
        if (user == null)throw new BaseException(403, "未登录");

        String receiverStr = request.getParameter("receiver");
        if(receiverStr==null)throw new BaseException(500, "数据异常");
        int receiverLevel = Integer.parseInt(receiverStr);
        int senderLevel = user.getLevel();
        String msg = request.getParameter("msg");
        int cnt = MessageDao.sendMsg(senderLevel, receiverLevel, msg);
        response.getWriter().print(JsonUtil.obj2String(new HashMap<String, Object>(){{
            put("code", cnt!=0?2000:0);
        }}));
    }

    public void getAction() throws IOException {
        User user = (User)request.getSession().getAttribute("user");
        if (user == null)throw new BaseException(403, "未登录");

        List<Message> messages = MessageDao.get(user.getLevel());

        response.getWriter().print(JsonUtil.obj2String(new HashMap<String, Object>(){{
            put("code", 2000);
            put("msg", messages);
        }}));
    }
}
