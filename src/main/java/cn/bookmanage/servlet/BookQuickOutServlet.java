package cn.bookmanage.servlet;

import cn.bookmanage.dao.BookDao;
import cn.bookmanage.entity.Book;
import cn.bookmanage.service.BookSystem.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="BookQuickOutServlet",urlPatterns ={"/BookQuickOutServlet"} )
public class BookQuickOutServlet extends HttpServlet {
    public void init(ServletConfig config)throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)throws ServletException, IOException {
        doPost(servletRequest,servletResponse);
    }

    public void doPost(HttpServletRequest servletRequest,HttpServletResponse servletResponse)throws IOException{
        servletResponse.setContentType("text/html;charset=utf-8");
        servletRequest.setCharacterEncoding("utf-8");

        BookDao.BookQuickOut();

    }
}
