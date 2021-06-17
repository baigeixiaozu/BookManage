package cn.bookmanage.servlet;


import cn.bookmanage.entity.User;
import cn.bookmanage.service.MarketingSystem.InsertByIDService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "MarketingServlet", value = "/MarketingServlet")
public class MarketingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int id = Integer.parseInt(request.getParameter("id"));
        User i = (User) request.getSession().getAttribute("user");

        InsertByIDService.Insert(id, i);

    }
}
