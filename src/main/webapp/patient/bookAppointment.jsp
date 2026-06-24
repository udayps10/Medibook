<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.medibook.model.User"%>
<%
    User u = (User) session.getAttribute("user");
    if(u == null || !u.getRole().equals("patient")) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
    String doctorId = request.getParameter("doctorId");
%>
<!DOCTYPE html>
<html>
<head><title>Book Appointment</title></head>
<body>
    <h2>Book Appointment</h2>
    <form action="../bookAppointment" method="post">
        <input type="hidden" name="doctorId" value="<%= doctorId %>">
        Date: <input type="date" name="appointmentDate"><br><br>
        Time Slot: 
        <select name="timeSlot">
            <option>09:00 AM</option>
            <option>10:00 AM</option>
            <option>11:00 AM</option>
            <option>02:00 PM</option>
            <option>03:00 PM</option>
        </select><br><br>
        Notes: <textarea name="notes"></textarea><br><br>
        <button type="submit">Book</button>
    </form>
</body>
</html>