<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.medibook.model.User"%>
<%@ page import="java.util.List"%>
<%
    User u = (User) session.getAttribute("user");
    if(u == null || !u.getRole().equals("admin")) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
    List<User> users = (List<User>) request.getAttribute("users");
%>
<!DOCTYPE html>
<html>
<head><title>Manage Users</title></head>
<body>
    <h2>All Users</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Role</th>
            <th>Phone</th>
            <th>Action</th>
        </tr>
        <% for(User user : users) { %>
        <tr>
            <td><%= user.getId() %></td>
            <td><%= user.getName() %></td>
            <td><%= user.getEmail() %></td>
            <td><%= user.getRole() %></td>
            <td><%= user.getPhone() %></td>
            <td>
                <a href="<%= request.getContextPath() %>/admin?action=deleteUser&id=<%= user.getId() %>"
                   onclick="return confirm('Are you sure?')">Delete</a>
            </td>
        </tr>
        <% } %>
    </table>
    <br>
    <a href="<%= request.getContextPath() %>/admin/dashboard.jsp">Back to Dashboard</a>
</body>
</html>