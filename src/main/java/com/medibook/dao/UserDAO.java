package com.medibook.dao;

import com.medibook.model.User;
import com.medibook.util.DBConnection;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class UserDAO {

    public boolean registerUser(User u) {
        String sql = "INSERT INTO users (name, email, password, role, phone) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getName());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getRole());
            ps.setString(5, u.getPhone());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUserByEmail(String email) {
        User user = null;
        String sql = "SELECT * FROM users WHERE email = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setPhone(rs.getString("phone"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setRole(rs.getString("role"));
                u.setPhone(rs.getString("phone"));
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}