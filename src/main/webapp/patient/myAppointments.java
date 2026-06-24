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
<head><title>My Appointments</title></head>
<body>
    <h2>My Appointments</h2>
    <% if(appointments == null || appointments.isEmpty()) { %>
        <p>No appointments yet.</p>
    <% } <%@ page language="java" contentType="text/html; charset=UTF-8"%>
    <%@ page import="com.medibook.model.User"%>
    <%@ page import="com.medibook.model.Appointment"%>
    <%@ page import="java.util.List"%>
    <%
        User u = (User) session.getAttribute("user");
        if(u == null || !u.getRole().equals("patient")) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        List<Appointment> appointments = (List<Appointment>) request.getAttribute("appointments");
    %>
    <!DOCTYPE html>
    <html>
    <head><title>My Appointments</title></head>
    <body>
        <h2>My Appointments</h2>
        <% if(appointments == null || appointments.isEmpty()) { %>
            <p>No appointments booked yet.</p>
        <% } else { %>
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Doctor ID</th>
                    <th>Date</th>
                    <th>Time Slot</th>
                    <th>Notes</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
                <% for(Appointment a : appointments) { %>
                <tr>
                    <td><%= a.getId() %></td>
                    <td><%= a.getDoctorId() %></td>
                    <td><%= a.getAppointmentDate() %></td>
                    <td><%= a.getTimeSlot() %></td>
                    <td><%= a.getNotes() %></td>
                    <td><%= a.getStatus() %></td>
                    <td>
                        <% if("pending".equals(a.getStatus()) || "confirmed".equals(a.getStatus())) { %>
                            <a href="<%= request.getContextPath() %>/cancelAppointment?id=<%= a.getId() %>">Cancel</a>
                        <% } else { %>
                            -
                        <% } %>
                    </td>
                </tr>
                <% } %>
            </table>
        <% } %>
        <br>
        <a href="<%= request.getContextPath() %>/patient/dashboard.jsp">Back to Dashboard</a>
    </body>
    </html>else { %>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Patient ID</th>
                <th>Date</th>
                <th>Time Slot</th>
                <th>Notes</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            <% for(Appointment a : appointments) { %>
            <tr>
                <td><%= a.getId() %></td>
                <td><%= a.getPatientId() %></td>
                <td><%= a.getAppointmentDate() %></td>
                <td><%= a.getTimeSlot() %></td>
                <td><%= a.getNotes() %></td>
                <td><%= a.getStatus() %></td>
                <td>
                    <% if("pending".equals(a.getStatus())) { %>
                        <a href="<%= request.getContextPath() %>/appointments?action=updateStatus&appointmentId=<%= a.getId() %>&status=confirmed">Confirm</a> |
                        <a href="<%= request.getContextPath() %>/appointments?action=updateStatus&appointmentId=<%= a.getId() %>&status=cancelled">Cancel</a>
                    <% } else { %>
                        <%= a.getStatus() %>
                    <% } %>
                </td>
            </tr>
            <% } %>
        </table>
    <% } %>
    <br>
    <a href="<%= request.getContextPath() %>/doctor/dashboard.jsp">Back to Dashboard</a>
</body>
</html>