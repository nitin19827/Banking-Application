<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Customer Form</title>
</head>
<body>
	<h2>Update Customer Details</h2>
    <%
        Object customerObj = request.getAttribute("customer");
        if (customerObj != null && customerObj instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, String> customer = (Map<String, String>) customerObj;
    %>
    <form action="UpdateCustomerDetailsServlet" method="post">
        <input type="hidden" name="accountno" value="<%= customer.get("account_no") %>">
        <label for="fullname">Full Name:</label>
        <input type="text" id="fullname" name="fullname" value="<%= customer.get("full_name") %>" required><br><br>
        <label for="address">Address:</label>
        <input type="text" id="address" name="address" value="<%= customer.get("address") %>" required><br><br>
        <label for="mobileno">Mobile No:</label>
        <input type="text" id="mobileno" name="mobileno" value="<%= customer.get("mobile_no") %>" required><br><br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="<%= customer.get("email") %>" required><br><br>
        <label for="accountType">Account Type:</label>
        <input type="text" id="accountType" name="accountType" value="<%= customer.get("account_type") %>" required><br><br>
        <label for="idProof">Id Proof:</label>
        <input type="text" id="idProof" name="idProof" value="<%= customer.get("id_proof") %>" required><br><br>
        <input type="submit" value="Update">
    </form>
    <%
        } else {
    %>
    <p>No customer details found. Please try again.</p>
    <%
        }
    %>
</body>
</html>