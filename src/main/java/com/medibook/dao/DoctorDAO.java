package com.medibook.dao;
import com.medibook.model.Doctor;
import com.medibook.util.DBConnection;
import java.sql.*;
import java.util.*;
public class DoctorDAO {
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM doctors";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Doctor d = new Doctor();
                d.setId(rs.getInt("id"));
                d.setName(rs.getString("name"));
                d.setEmail(rs.getString("email"));
                d.setPassword(rs.getString("password"));
                d.setPhone(rs.getString("phone"));
                d.setSpecialization(rs.getString("specialization"));
                d.setQualification(rs.getString("qualification"));
                d.setHospitalName(rs.getString("hospital_name"));
                d.setExperienceYears(rs.getInt("experience_years"));
                d.setConsultationFee(rs.getDouble("consultation_fee"));
                d.setAvailable(rs.getBoolean("available"));
                doctors.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }
    
    public Doctor getDoctorById(int id) {
        String sql = "SELECT * FROM doctors WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    Doctor d = new Doctor();

                    d.setId(rs.getInt("id"));
                    d.setName(rs.getString("name"));
                    d.setEmail(rs.getString("email"));
                    d.setPassword(rs.getString("password"));
                    d.setPhone(rs.getString("phone"));
                    d.setSpecialization(rs.getString("specialization"));
                    d.setQualification(rs.getString("qualification"));
                    d.setHospitalName(rs.getString("hospital_name"));
                    d.setExperienceYears(rs.getInt("experience_years"));
                    d.setConsultationFee(rs.getDouble("consultation_fee"));
                    d.setAvailable(rs.getBoolean("available"));

                    return d;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    	
    	
    	
    }
