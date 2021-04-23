package cn.bookmanage.listener;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebListener
public class AppListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    public AppListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // TODO: 初始化数据目录，以及其它信息
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
        // ServletContext servletContext = sce.getServletContext();
        // String basePath = servletContext.getContextPath();
        // System.out.println(basePath);
        // servletContext.setInitParameter("LOG_PATH", basePath + "/../logs");
        // System.out.println(servletContext.getInitParameter("LOG_PATH"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is added to a session. */
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is replaced in a session. */
    }
}
