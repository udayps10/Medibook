<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>MediBook - Register</title></head>
<body>
    <h2>Register</h2>
    <% if(request.getParameter("error") != null) { %>
        <p style="color:red;">Email already exists. Try another.</p>
    <% } %>
    <form action="auth" method="post">
        <input type="hidden" name="action" value="register">
        Name: <input type="text" name="name"><br><br>
        Email: <input type="text" name="email"><br><br>
        Password: <input type="password" name="password"><br><br>
        Phone: <input type="text" name="phone"><br><br>
        Role:
        <select name="role">
            <option value="patient">Patient</option>
            <option value="doctor">Doctor</option>
        </select><br><br>
        <button type="submit">Register</button>
    </form>
    <a href="login.jsp">Already have an account? Login</a>
</body>
</html>