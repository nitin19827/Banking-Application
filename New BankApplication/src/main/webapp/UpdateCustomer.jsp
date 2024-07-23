<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Customer</title>
</head>
<body>
	<h1>Update Customer Details</h1>
    <form action="UpdateCustomerDetailsServlet" method="get">
        <label for="accountno">Account Number:</label>
        <input type="text" id="accountno" name="accountno" required><br><br>
        <input type="submit" value="View Customer">
    </form>
    <br>

    <div>
        <p><%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %></p>
    </div>
</body>
</html>