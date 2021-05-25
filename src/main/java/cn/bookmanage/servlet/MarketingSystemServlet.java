package cn.bookmanage.servlet;

import cn.bookmanage.entity.OrderBookList;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MarketingSystemServlet", value = "/MarketingSystemServlet")
public class MarketingSystemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String[] bookName=request.getParameterValues("bookName[]");
        String[] bookCount=request.getParameterValues("bookCount[]");
        OrderBookList user=new OrderBookList(bookName, bookCount);



        ObjectMapper mapper = new ObjectMapper(); //Jackson的核心类
        String json = mapper.writeValueAsString(user);
        System.out.println(json+"\n");

        PrintWriter out = response.getWriter();
        out.print(json);
    }
}
