package com.medibook.service;

import com.medibook.dao.AppointmentDAO;
import com.medibook.model.Appointment;

public class AppointmentService {

    private AppointmentDAO appointmentDAO = new AppointmentDAO();

    public boolean bookAppointment(Appointment a) {
        if (appointmentDAO.isSlotTaken(
                a.getDoctorId(),
                a.getAppointmentDate(),
                a.getTimeSlot())) {
            return false;
        }

        a.setStatus("pending");
        return appointmentDAO.bookAppointment(a);
    }
}