<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.medibook.model.User"%>
<%
    User u = (User) session.getAttribute("user");
    if(u == null || !u.getRole().equals("admin")) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head><title>Admin Dashboard</title></head>
<body>
    <h2>Welcome Admin <%= u.getName() %>!</h2>
    <nav>
        <a href="<%= request.getContextPath() %>/admin?action=users">Manage Users</a> |
        <a href="<%= request.getContextPath() %>/admin?action=doctors">Manage Doctors</a> |
        <a href="<%= request.getContextPath() %>/logout">Logout</a>
    </nav>
    <hr>
    <p>Select an option above to manage the system.</p>
</body>
</html>