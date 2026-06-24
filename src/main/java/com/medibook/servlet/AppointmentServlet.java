package com.medibook.servlet;

import com.medibook.dao.AppointmentDAO;
import com.medibook.model.Appointment;
import com.medibook.model.User;
import com.medibook.service.AppointmentService;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class AppointmentServlet extends HttpServlet {

    // Patient books appointment
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        User u = (User) req.getSession().getAttribute("user");
        if (u == null || !u.getRole().equals("patient")) {
            res.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        Appointment a = new Appointment();
        a.setPatientId(u.getId());
        a.setDoctorId(Integer.parseInt(req.getParameter("doctorId")));
        a.setAppointmentDate(req.getParameter("appointmentDate"));
        a.setTimeSlot(req.getParameter("timeSlot"));
        a.setNotes(req.getParameter("notes"));

        AppointmentService service = new AppointmentService();
        boolean success = service.bookAppointment(a);

        if (success) {
            res.sendRedirect(req.getContextPath() + "/patient/bookingConfirmed.jsp");
        } else {
            res.sendRedirect(req.getContextPath() + "/patient/bookAppointment.jsp?error=slotTaken&doctorId=" + a.getDoctorId());
        }
    }

    // Doctor views appointments OR updates status
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        User u = (User) req.getSession().getAttribute("user");
        if (u == null) {
            res.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        String action = req.getParameter("action");

        // Doctor updating appointment status
        if ("updateStatus".equals(action)) {
            int appointmentId = Integer.parseInt(req.getParameter("appointmentId"));
            String status = req.getParameter("status");
            AppointmentDAO dao = new AppointmentDAO();
            dao.updateStatus(appointmentId, status);
            res.sendRedirect(req.getContextPath() + "/appointments?action=doctorView");
            return;
        }

        // Doctor viewing their appointments
        if ("doctorView".equals(action)) {
            AppointmentDAO dao = new AppointmentDAO();
            List<Appointment> list = dao.getAppointmentsByDoctorId(u.getId());
            req.setAttribute("appointments", list);
            req.getRequestDispatcher("/doctor/myAppointments.jsp").forward(req, res);
            return;
        }

        // Patient viewing their appointments
        if ("patientView".equals(action)) {
            AppointmentDAO dao = new AppointmentDAO();
            List<Appointment> list = dao.getAppointments(u.getId());
            req.setAttribute("appointments", list);
            req.getRequestDispatcher("/patient/myAppointments.jsp").forward(req, res);
            return;
        }
    }
}