package cn.bookmanage.servlet;
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
import java.io.Writer;

@WebServlet(name="BookServlet",urlPatterns ={"/BookServlet"} )
public class BookServlet extends HttpServlet{
    public void init(ServletConfig config)throws ServletException{
        super.init(config);
    }

    public void doGet(HttpServletRequest servletRequest,HttpServletResponse servletResponse)throws ServletException,IOException{
        doPost(servletRequest,servletResponse);
    }

    public void doPost(HttpServletRequest servletRequest,HttpServletResponse servletResponse)throws IOException{
        servletResponse.setContentType("text/html;charset=utf-8");
        servletRequest.setCharacterEncoding("utf-8");

        ServletInputStream inputStream=servletRequest.getInputStream();

        String json=null;
        String status="false";
        byte[] chunk=new byte[1024];
        while(inputStream.read(chunk)>0){ }
        json=new String(chunk, "utf-8");

        ObjectMapper mapper=new ObjectMapper();
        Book book=mapper.readValue(json,Book.class);

        status=BookService.BookIO(book);
        //json=json+"\"status\":"+status;//这样处理，status两边就有双引号了
        servletResponse.getWriter().write(status);
    }
}
