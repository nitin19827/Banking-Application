<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Login</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #D2D2B6;
        margin: 10px;
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .login-box {
        background-color: #EAE5E4;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        width: 300px;
        text-align: center;
    }
    h2 {
        margin-bottom: 20px;
    }
    label {
        display: block;
        margin-bottom: 5px;
        text-align: left;
    }
    input[type="text"],
    input[type="password"] {
        width: calc(100% - 20px);
        padding: 8px 10px;
        margin-bottom: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-sizing: border-box;
    }
    input[type="submit"] {
        width: 100%;
        padding: 10px 0;
        background-color: #007BFF;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }
    input[type="submit"]:hover {
        background-color: #0056b3;
    }
</style>
</head>
<body>
<div class="login-box">
    <h2>Customer Login</h2>
    <form action="CustomerLoginServlet" method="post">
        <label for="account_no">Account Number:</label>
        <input type="text" id="account_no" name="account_no" required><br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>
        <input type="submit" value="Login">
    </form>
</div>
</body>
</html>
