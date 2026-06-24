<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.medibook.model.User"%>
<%@ page import="com.medibook.model.Doctor"%>
<%@ page import="java.util.List"%>
<%
    User u = (User) session.getAttribute("user");
    if(u == null || !u.getRole().equals("admin")) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
    List<Doctor> doctors = (List<Doctor>) request.getAttribute("doctors");
%>
<!DOCTYPE html>
<html>
<head><title>Manage Doctors</title></head>
<body>
    <h2>All Doctors</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Specialization</th>
            <th>Hospital</th>
            <th>Fee</th>
        </tr>
        <% for(Doctor d : doctors) { %>
        <tr>
            <td><%= d.getId() %></td>
            <td><%= d.getName() %></td>
            <td><%= d.getEmail() %></td>
            <td><%= d.getSpecialization() %></td>
            <td><%= d.getHospitalName() %></td>
            <td><%= d.getConsultationFee() %></td>
        </tr>
        <% } %>
    </table>
    <br>
    <a href="<%= request.getContextPath() %>/admin/dashboard.jsp">Back to Dashboard</a>
</body>
</html>