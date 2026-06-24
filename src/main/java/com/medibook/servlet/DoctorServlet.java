package com.medibook.servlet;

import com.medibook.dao.DoctorDAO;
import com.medibook.model.Doctor;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class DoctorServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        DoctorDAO doctorDAO = new DoctorDAO();
        List<Doctor> doctors = doctorDAO.getAllDoctors();
        
        req.setAttribute("doctors", doctors);
        req.getRequestDispatcher("/patient/viewDoctors.jsp").forward(req, res);
    }
}