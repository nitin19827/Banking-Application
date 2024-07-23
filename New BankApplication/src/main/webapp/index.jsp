<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bank Application</title>
<style>
body {
    font-family: Arial, sans-serif;
    background-color: #D2D2B6;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
}

.container {
    background-color: #EAE5E4;
    padding: 40px;
    box-shadow: 1 3 10px rgba(0, 0, 0, 0.1);
    border-radius: 7px;
    text-align: center;
}

h1 {
    margin-bottom: 20px;
}

.checkbox-group {
    margin-bottom: 20px;
}

label {
    margin-right: 10px;
    padding: 10px 20px;
    border: 1px solid #000;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s, color 0.3s, border-color 0.3s;
}

label:hover {
    background-color: #007BFF;
    color: #fff;
    border-color: #007BFF;
}

button {
    padding: 10px 20px;
    background-color: #007BFF;
    color: #fff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
}

button:hover {
    background-color: #0056b3;
}

</style>
</head>
<body>
    <div class="container">
        <h1>Banking Application</h1>
        <form method="post">
            <div class="checkbox-group">
                <label>
                    <input type="radio" name="userType" value="admin" required>
                    Admin
                </label>
                <label>
                    <input type="radio" name="userType" value="customer" required>
                    Customer
                </label>
            </div>
            <button type="submit">Login</button>
        </form>
        <%
            String userType = request.getParameter("userType");
            if (userType != null) {
                if ("admin".equals(userType)) {
                    response.sendRedirect("AdminLogin.jsp");
                } else if ("customer".equals(userType)) {
                    response.sendRedirect("CustomerLogin.jsp");
                } else {
                    out.println("Invalid user type.");
                }
            }
        %>
    </div>
</body>
</html>
