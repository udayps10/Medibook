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