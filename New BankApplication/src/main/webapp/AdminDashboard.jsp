<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        padding: 20px;
        position: relative;
    }
    .logout-button {
        position: absolute;
        top: 10px;
        right: 10px;
        padding: 10px 20px;
        background-color: #dc3545;
        border: 2px solid #c82333;
        border-radius: 5px;
        color: white;
        text-decoration: none;
    }
    .logout-button:hover {
        background-color: #c82333;
        border-color: #9f1c22;
    }
    .dashboard-links {
        margin-top: 20px;
    }
    .dashboard-links a {
        display: inline-block;
        margin-bottom: 10px;
        padding: 10px 20px;
        background-color: #007BFF;
        color: white;
        text-decoration: none;
        border: 2px solid #0056b3;
        border-radius: 5px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        white-space: nowrap; /* Prevents the text from wrapping */
    }
    .dashboard-links a:hover {
        background-color: #0056b3;
        border-color: #003f7f;
    }
</style>
</head>
<body>
<div class="dashboard-links">
    <a href="NewCustomerFromAdmin.jsp">New Customer</a>
    <a href="CustomerDetails.jsp">See Customer Details</a>
    <a href="DeleteCustomer.jsp">Delete Customer</a>
    <a href="UpdateCustomer.jsp">Update Customer Details</a>
</div>
<a href="AdminLogoutServlet" class="logout-button">Log Out</a>
</body>
</html>
