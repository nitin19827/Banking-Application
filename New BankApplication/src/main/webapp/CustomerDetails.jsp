<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ICustomer Details</title>

</head>
<body>
   <h1>Customer Details</h1>
    <form action="CustomerDetailsServlet" method="post">
        <label for="accountno">Account Number:</label>
        <input type="text" id="accountno" name="accountno" required><br><br>
        <input type="submit" value="View Customer">
    </form>
</body>
</html>