<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.medibook.model.User"%>
<%
    User u = (User) session.getAttribute("user");
    if(u == null || !u.getRole().equals("patient")) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head><title>Patient Dashboard</title></head>
<body>
    <h2>Welcome, <%= u.getName() %>!</h2>
    <nav>
        <a href="viewDoctors.jsp">View Doctors</a> |
        <a href="myAppointments.jsp">My Appointments</a> |
        <a href="../logout">Logout</a>
    </nav>
    <hr>
    <p>What would you like to do today?</p>
</body>
</html>