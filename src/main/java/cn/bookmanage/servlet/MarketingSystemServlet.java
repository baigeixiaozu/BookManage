package cn.bookmanage.servlet;

import cn.bookmanage.entity.OrderBookList;
import cn.bookmanage.service.MarketingSystem.InsertByNameService;
import cn.bookmanage.service.MarketingSystem.InsertByNameService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

import cn.bookmanage.service.MarketingSystem.showLackBooks;

@WebServlet(name = "MarketingSystemServlet", value = "/MarketingSystemServlet")
public class MarketingSystemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String[] bookName = request.getParameterValues("bookName[]");
        String[] bookCount = request.getParameterValues("bookCount[]");
        OrderBookList user = new OrderBookList(bookName, bookCount);

        System.out.println(user);


        int[] lack = new int[6];
        for (int i = 0; i < 6; i++) {
            lack[i] = 0;
        }
        lack = InsertByNameService.Insert(bookName, bookCount);
        StringBuilder text = new StringBuilder("目前暂缺的书籍:"+"\n");
        for (int i = 0; i < lack.length; i++) {
            if (lack[i] != 0) {
                text.append(showLackBooks.show(i));
            }
        }


        String message = text.toString();
        PrintWriter out = response.getWriter();
        out.print(message);
    }
}
