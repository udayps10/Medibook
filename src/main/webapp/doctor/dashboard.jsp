<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.medibook.model.User"%>
<%
    User u = (User) session.getAttribute("user");
    if(u == null || !u.getRole().equals("doctor")) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head><title>Doctor Dashboard</title></head>
<body>
    <h2>Welcome Dr. <%= u.getName() %>!</h2>
    <nav>
    <a href="<%= request.getContextPath() %>/appointments?action=doctorView">My Appointments</a> |
        <a href="mySchedule.jsp">My Schedule</a> |
        <a href="../logout">Logout</a>
    </nav>
    <hr>
    <p>Manage your appointments here.</p>
</body>
</html>