package com.demon.mall.Filter;

import com.demon.mall.Common.Encryption;
import com.demon.mall.Service.UserService;
import com.demon.mall.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class CheckoutFilter implements Filter {
    @Autowired
    UserService userService;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest=(HttpServletRequest)servletRequest;
        HttpSession session = httpServletRequest.getSession();
        User CurrentUser = (User) session.getAttribute(Encryption.User_login);
        if (CurrentUser == null) {
            PrintWriter out = new HttpServletResponseWrapper((HttpServletResponse)servletResponse).getWriter();
            out.write("{\n" +
                    "    \"status\": 10008,\n" +
                    "    \"msg\": \"NEED_LOGIN\",\n" +
                    "    \"data\": null\n" +
                    "}");
            out.flush();
            out.close();
            return;

        }
        boolean b = userService.CheckAdminRole(CurrentUser);
        if (b == false) {
            PrintWriter out = new HttpServletResponseWrapper((HttpServletResponse)servletResponse).getWriter();
            out.write("{\n" +
                    "    \"status\": 10009,\n" +
                    "    \"msg\": \"NEED_ADMIN\",\n" +
                    "    \"data\": null\n" +
                    "}");
            out.flush();
            out.close();
            return;
        }
        //必须要放行，不然程序会卡在这个地方
     filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
