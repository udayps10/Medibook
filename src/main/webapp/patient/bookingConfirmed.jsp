<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.medibook.model.User"%>
<%
    User u = (User) session.getAttribute("user");
    if(u == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head><title>Booking Confirmed</title></head>
<body>
    <h2>✅ Appointment Booked Successfully!</h2>
    <p>Your appointment is pending confirmation from the doctor.</p>
    <a href="<%= request.getContextPath() %>/patient/dashboard.jsp">Back to Dashboard</a>
</body>
</html>