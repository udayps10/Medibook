<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>MediBook - Login</title></head>
<body>
    <h2>Login</h2>
    <% if(request.getParameter("error") != null) { %>
        <p style="color:red;">Invalid email or password.</p>
    <% } %>
    <form action="auth" method="post">
        <input type="hidden" name="action" value="login">
        Email: <input type="text" name="email"><br><br>
        Password: <input type="password" name="password"><br><br>
        <button type="submit">Login</button>
    </form>
    <a href="register.jsp">Don't have an account? Register</a>
</body>
</html>