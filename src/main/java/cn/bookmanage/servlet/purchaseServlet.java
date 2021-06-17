package cn.bookmanage.servlet;

import cn.bookmanage.entity.User;
import cn.bookmanage.entity.info;
import cn.bookmanage.service.purchasing.PurchasingServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name="purchaseServlet" ,urlPatterns={"/purchaseServlet"})
public class purchaseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PurchasingServices ps=new PurchasingServices();
        String content=request.getParameter("content");
        String receiver=request.getParameter("receiver");
        String sender=request.getParameter("sender");
        ArrayList<info> samp= new ArrayList<info>();
        info sample=new info();
        sample.setTime(null);
        sample.setContent(content);
        sample.setReceiver(receiver);
        Long temp=ps.search_id();
        temp++;
        sample.setInfo_id(temp);
        sample.setSender(sender);
        samp.add(sample);
        ps.store(samp);
        // response.sendRedirect("PurchasingSystem/confirm.jsp");

        System.out.println("What happened ???");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
