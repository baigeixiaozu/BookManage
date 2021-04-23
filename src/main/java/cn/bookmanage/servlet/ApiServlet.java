package cn.bookmanage.servlet;

import cn.bookmanage.utils.ApiUtil;
import cn.bookmanage.utils.JsonUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 拦截所有API请求，进行路由处理
 *
 */
@WebServlet(name = "ApiServlet", value = "/Api/*")
public class ApiServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public ApiServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String pkg = null;
        Map<String, Object> ja = new HashMap<>();

        // info 用于存储请求信息
        Map<String, Object> info = new HashMap<>();
        info.put("request", request);
        info.put("response", response);

        // 拦截API请求处理过程中产生的异常，确保客户端收到的数据格式为JSON。
        try {
            String uri = request.getServletPath() + ((null == request.getPathInfo())?"":request.getPathInfo());
            System.out.println(uri);

            // generate route
            List<Map<String, String>> parsedURI = ApiUtil.parseURI(uri);
            if(null == parsedURI) throw new RuntimeException("URI解析失败");

            Map<String, String> params = parsedURI.get(1);
            info.put("param", params);

            Map<String, String> route = parsedURI.get(0);

            // 服务包
            String basePkg = "cn.bookmanage.service";
            // 构造试图请求的类
            pkg = basePkg + "." + route.get("subPkg") + ".impl." + route.get("service") + "ServiceImpl";

            // 从这里开始使用反射来自动创建类，以及调用方法
            // transfer method by reflection
            // 尝试加载请求的类
            Class<?> clazz = Class.forName(pkg);
            // 尝试获取 请求的类中 含有map参数的构造方法
            Constructor<?> construct = clazz.getConstructor(Map.class);
            // 创建实例对象
            Object obj = construct.newInstance(info);
            // 尝试获取指定 "无参" 方法
            Method method = clazz.getMethod(route.get("action") + "Action");
            // 尝试调用 指定无参方法
            method.invoke(obj);

        } catch (ClassNotFoundException e) {
            // TODO: errCode
            e.printStackTrace();

            ja.put("errCode", 10404);
            ja.put("errMsg", pkg + " - 未找到指定类");
            response.getWriter().print(JsonUtil.obj2String(ja));

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();

            ja.put("errCode", 10404);
            ja.put("errMsg", e.getMessage() + "方法未找到，或其它异常");
            response.getWriter().print(JsonUtil.obj2String(ja));
        } catch (Exception e) {
            e.printStackTrace();

            ja.put("errCode", 10500);
            ja.put("errMsg", e.getLocalizedMessage() + "未知异常");
            response.getWriter().print(JsonUtil.obj2String(ja));
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
