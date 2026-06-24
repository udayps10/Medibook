package com.medibook.servlet;
import com.medibook.service.UserService;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.medibook.model.User;

import java.io.IOException;

public class AuthServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {
        
        String action = req.getParameter("action");
        
        if ("register".equals(action)) {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String role = req.getParameter("role");
            String phone = req.getParameter("phone");
            User u = new User();
            u.setName(name);
            u.setEmail(email);
            u.setPassword(password);
            u.setRole(role);
            u.setPhone(phone); 
            UserService userService = new UserService();
            boolean success = userService.registerUser(u);

            if (success) {
                res.sendRedirect("login.jsp");
            } else {
                res.sendRedirect("register.jsp?error=emailExists");
            }
        }
        else if ("login".equals(action)) {
            String email = req.getParameter("email");
            
            String password = req.getParameter("password");
            UserService userService = new UserService();
            
           User user = userService.loginUser(email,password);
           if (user != null) {
        	   HttpSession session = req.getSession();
        	   req.getSession().setAttribute("user", user);
        	   if (user.getRole().equals("patient")) {
        		    res.sendRedirect("patient/dashboard.jsp");
        		} else if (user.getRole().equals("doctor")) {
        		    res.sendRedirect("doctor/dashboard.jsp");
        		} else if (user.getRole().equals("admin")) {
        		    res.sendRedirect("admin/dashboard.jsp");
        		}
           }else {
			   res.sendRedirect("login.jsp?error=invalidCredentials");
           }
            
           
            		
        }
    }
}