<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Login</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
    }

    .container {
        display: flex;
        justify-content: space-between;
        align-items: center;
        height: 100vh;
    }

    .left-section {
        flex: 1;
        padding: 20px;
    }

    .left-section img {
        max-width: 100%;
        height: auto;
    }

    .right-section {
        flex: 1;
        padding: 20px;
    }

    .right-section h1 {
        font-size: 36px; /* Adjust size as needed */
    }

    .right-section form {
        max-width: 400px;
        margin: 0 auto;
        background-color: #fff;
        padding: 20px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        border-radius: 5px;
    }

    .right-section form table {
        width: 100%;
    }

    .right-section form input[type="text"],
    .right-section form input[type="password"] {
        width: calc(100% - 10px);
        padding: 10px;
        margin: 5px;
        border: 1px solid #ccc;
        border-radius: 3px;
    }

    .right-section form .submit-button {
        padding: 10px 20px;
        background-color: #007BFF;
        color: #fff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    .right-section form .submit-button:hover {
        background-color: #0056b3;
    }
</style>
</head>
<body>
<div class="container">
  <div class="left-section">
    <!-- You can replace this with your actual image -->
    <img src="https://via.placeholder.com/300" alt="Image Placeholder">
  </div>
  <div class="right-section">
    <div align="center">
      <h1>Login Form</h1>
      <form action="<%=request.getContextPath()%>/AdminLogin" method="post">
       <table style="width: 100%">
        <tr>
         <td>UserName</td>
         <td><input type="text" name="username" /></td>
        </tr>
        <tr>
         <td>Password</td>
         <td><input type="password" name="password" /></td>
        </tr>
       </table>
       <input type="submit" value="Submit" class="submit-button" />
      </form>
     </div>
  </div>
 </div>
</body>
</html>