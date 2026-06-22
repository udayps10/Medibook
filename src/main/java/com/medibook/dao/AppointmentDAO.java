package com.medibook.dao;

import com.medibook.model.Appointment;
import com.medibook.util.DBConnection;
import java.sql.*;
import java.util.*;

public class AppointmentDAO {

    public boolean bookAppointment(Appointment a) {
        String sql = "INSERT INTO appointments "
                   + "(patient_id, doctor_id, appointment_date, time_slot, status, notes) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, a.getPatientId());
            ps.setInt(2, a.getDoctorId());
            ps.setString(3, a.getAppointmentDate());
            ps.setString(4, a.getTimeSlot());
            ps.setString(5, a.getStatus());
            ps.setString(6, a.getNotes());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Appointment> getAppointments(int patientId) {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments WHERE patient_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, patientId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Appointment a = new Appointment();
                a.setId(rs.getInt("id"));
                a.setPatientId(rs.getInt("patient_id"));
                a.setDoctorId(rs.getInt("doctor_id"));
                a.setAppointmentDate(rs.getString("appointment_date"));
                a.setTimeSlot(rs.getString("time_slot"));
                a.setStatus(rs.getString("status"));
                a.setNotes(rs.getString("notes"));
                a.setCreatedAt(rs.getString("created_at"));
                appointments.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    public boolean cancelAppointment(int id) {
        String sql = "UPDATE appointments SET status = 'cancelled' WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
public boolean isSlotTaken(int doctorId, String date, String timeSlot) {

    String sql = "SELECT * FROM appointments "
               + "WHERE doctor_id = ? "
               + "AND appointment_date = ? "
               + "AND time_slot = ? "
               + "AND status != 'cancelled'";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, doctorId);
        ps.setString(2, date);
        ps.setString(3, timeSlot);

        ResultSet rs = ps.executeQuery();

        return rs.next(); 
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}}