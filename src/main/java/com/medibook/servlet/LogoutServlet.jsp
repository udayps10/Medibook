package com.medibook.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.getSession().invalidate();
        res.sendRedirect(req.getContextPath() + "/login.jsp");
    }
}