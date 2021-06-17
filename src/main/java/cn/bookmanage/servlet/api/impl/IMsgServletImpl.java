package cn.bookmanage.servlet.api.impl;

import cn.bookmanage.dao.MessageDao;
import cn.bookmanage.entity.User;
import cn.bookmanage.servlet.api.IMsgServlet;
import cn.bookmanage.utils.JsonUtil;

import java.io.IOException;
import java.util.HashMap;

/**
 * @Author jiyec
 * @Date 2021/6/17 18:56
 * @Version 1.0
 **/
public class IMsgServletImpl extends APIBaseServletImpl implements IMsgServlet{
    public void sengMsgAction() throws IOException {
        String receiverStr = request.getParameter("receiver");
        int receiverId  = Integer.parseInt(receiverStr);
        User user = (User)request.getSession().getAttribute("user");
        int senderId = user.getId();
        String msg = request.getParameter("msg");
        int cnt = MessageDao.sendMsg(senderId, receiverId, msg);

        response.getWriter().print(JsonUtil.obj2String(new HashMap<String, Object>(){{
            put("code", cnt==1?2000:-1);
        }}));
    }
}
