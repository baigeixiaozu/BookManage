package cn.bookmanage.servlet;

import cn.bookmanage.entity.info;
import cn.bookmanage.service.purchasing.PurchasingServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

@WebServlet(name="orderconfirm" ,urlPatterns={"/orderconfirm"})
public class orderconfirm extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PurchasingServices ps=new PurchasingServices();
        BigInteger jsp= new BigInteger(request.getParameter("jsp"));
        BigInteger cs= new BigInteger(request.getParameter("cs"));
        BigInteger se= new BigInteger(request.getParameter("se"));
        BigInteger web= new BigInteger(request.getParameter("web"));
        BigInteger english= new BigInteger(request.getParameter("english"));
        BigInteger po= new BigInteger(request.getParameter("po"));
        ps.purchase(1,jsp);
        ps.purchase(2,cs);
        ps.purchase(3,se);
        ps.purchase(4,web);
        ps.purchase(5,english);
        ps.purchase(6,po);
        response.sendRedirect("PurchasingSystem/confirm.jsp");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
