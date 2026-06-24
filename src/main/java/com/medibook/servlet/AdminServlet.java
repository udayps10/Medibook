package com.medibook.servlet;

import com.medibook.dao.AppointmentDAO;
import com.medibook.dao.DoctorDAO;
import com.medibook.dao.UserDAO;
import com.medibook.model.Appointment;
import com.medibook.model.Doctor;
import com.medibook.model.User;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class AdminServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        User u = (User) req.getSession().getAttribute("user");
        if (u == null || !u.getRole().equals("admin")) {
            res.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        String action = req.getParameter("action");

        if ("users".equals(action)) {
            List<User> users = new UserDAO().getAllUsers();
            req.setAttribute("users", users);
            req.getRequestDispatcher("/admin/manageUsers.jsp").forward(req, res);
            return;
        }

        if ("doctors".equals(action)) {
            List<Doctor> doctors = new DoctorDAO().getAllDoctors();
            req.setAttribute("doctors", doctors);
            req.getRequestDispatcher("/admin/manageDoctors.jsp").forward(req, res);
            return;
        }

        if ("deleteUser".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            new UserDAO().deleteUser(id);
            res.sendRedirect(req.getContextPath() + "/admin?action=users");
            return;
        }
        if ("cancel".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            new AppointmentDAO().cancelAppointment(id);
            res.sendRedirect(req.getContextPath() + "/appointments?action=patientView");
            return;
        }
        res.sendRedirect(req.getContextPath() + "/admin/dashboard.jsp");
    }
}