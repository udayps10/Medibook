<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.medibook.model.User"%>
<%@ page import="com.medibook.model.Doctor"%>
<%@ page import="java.util.List"%>
<%
    User u = (User) session.getAttribute("user");
    if(u == null || !u.getRole().equals("patient")) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
    List<Doctor> doctors = (List<Doctor>) request.getAttribute("doctors");
%>
<!DOCTYPE html>
<html>
<head><title>View Doctors</title></head>
<body>
    <h2>Available Doctors</h2>
    <% for(Doctor d : doctors) { %>
        <div>
            <h3><%= d.getName() %></h3>
            <p>Specialization: <%= d.getSpecialization() %></p>
            <p>Fee: <%= d.getConsultationFee() %></p>
            <p>Hospital: <%= d.getHospitalName() %></p>
            <a href="bookAppointment.jsp?doctorId=<%= d.getId() %>">Book</a>
        </div>
        <hr>
    <% } %>
    <a href="dashboard.jsp">Back</a>
</body>
</html>