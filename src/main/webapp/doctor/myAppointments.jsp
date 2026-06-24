<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.medibook.model.User"%>
<%@ page import="com.medibook.model.Appointment"%>
<%@ page import="java.util.List"%>
<%
    User u = (User) session.getAttribute("user");
    if(u == null || !u.getRole().equals("doctor")) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
    List<Appointment> appointments = (List<Appointment>) request.getAttribute("appointments");
%>
<!DOCTYPE html>
<html>
<head><title>My Schedule</title></head>
<body>
    <h2>My Appointments</h2>
    <% if(appointments == null || appointments.isEmpty()) { %>
        <p>No appointments.</p>
    <% } else { %>
        <% for(Appointment a : appointments) { %>
            <div>
                <p>Date: <%= a.getAppointmentDate() %></p>
                <p>Time: <%= a.getTimeSlot() %></p>
                <p>Status: <%= a.getStatus() %></p>
                <a href="../appointments?action=updateStatus&appointmentId=<%= a.getId() %>&status=confirmed">Confirm</a> |
                <a href="../appointments?action=updateStatus&appointmentId=<%= a.getId() %>&status=cancelled">Cancel</a>
            </div>
            <hr>
        <% } %>
    <% } %>
    <a href="dashboard.jsp">Back</a>
</body>
</html>