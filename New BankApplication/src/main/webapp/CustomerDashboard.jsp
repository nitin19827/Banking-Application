<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, javax.servlet.http.*, javax.servlet.*" %>
<%
HttpSession currentSession = request.getSession(false);
if (currentSession == null || currentSession.getAttribute("account_no") == null) {
    response.sendRedirect("CustomerLogin.jsp");
    return;
}

String accountNo = (String) currentSession.getAttribute("account_no");

Connection conn = null;
PreparedStatement stmt = null;
ResultSet rs = null;

String url = "jdbc:mysql://localhost:3306/mydb2";
String user = "root";
String password = "Nitin@123";

String query = "SELECT full_name, address, mobile_no, email, account_type, initial_balance, dob, id_proof FROM customers WHERE account_no = ?";

try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    conn = DriverManager.getConnection(url, user, password);
    stmt = conn.prepareStatement(query);
    stmt.setString(1, accountNo);

    // Debug: Print the account number being queried
    System.out.println("Querying account number: " + accountNo);

    rs = stmt.executeQuery();

    if (rs.next()) {
%>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #D2D2B6;
            display: flex;
            justify-content: center;
            align-items: flex-start;
            height: 100vh;
            margin: 0;
        }

        .container {
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
            text-align: center;
            position: relative;
            width: 80%;
            max-width: 600px;
        }

        .logout-button {
            position: absolute;
            top: 10px;
            right: 10px;
        }

        h2 {
            margin-top: 40px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }

        th {
            background-color: #f2f2f2;
        }

        form {
            margin: 20px 0;
        }

        input[type="submit"], input[type="number"] {
            padding: 5px;
            margin-top: 10px;
            background-color: white;
            color: black;
            border-colour: black;
            border-radius: 10px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #5BBDDF;
        }
    </style>
</head>
<body>
    <div class="container">
        <form action="LogoutServlet" method="post" class="logout-button">
            <input type="submit" value="Logout">
        </form>
        <h2>Customer Dashboard</h2>
        <p>Welcome, <%= rs.getString("full_name") %></p>
        <table>
            <tr>
                <th>Account Number</th>
                <td><%= accountNo %></td>
            </tr>
            <tr>
                <th>Full Name</th>
                <td><%= rs.getString("full_name") %></td>
            </tr>
            <tr>
                <th>Address</th>
                <td><%= rs.getString("address") %></td>
            </tr>
            <tr>
                <th>Mobile Number</th>
                <td><%= rs.getString("mobile_no") %></td>
            </tr>
            <tr>
                <th>Email ID</th>
                <td><%= rs.getString("email") %></td>
            </tr>
            <tr>
                <th>Account Type</th>
                <td><%= rs.getString("account_type") %></td>
            </tr>
            <tr>
                <th>Balance</th>
                <td><%= rs.getBigDecimal("initial_balance") %></td>
            </tr>
            <tr>
                <th>Date of Birth</th>
                <td><%= rs.getDate("dob") %></td>
            </tr>
            <tr>
                <th>ID Proof</th>
                <td><%= rs.getString("id_proof") %></td>
            </tr>
        </table>
        <h3>Account Actions</h3>
        <form action="DepositeServlet" method="post">
            <input type="hidden" name="account_no" value="<%= accountNo %>">
            <label for="deposit_amount">Deposit Amount:</label>
            <input type="number" id="deposit_amount" name="deposit_amount" required>
            <input type="submit" value="Deposit">
        </form>
        <form action="WithdrawServlet" method="post">
            <input type="hidden" name="account_no" value="<%= accountNo %>">
            <label for="withdraw_amount">Withdraw Amount:</label>
            <input type="number" id="withdraw_amount" name="withdraw_amount" required>
            <input type="submit" value="Withdraw">
        </form>
        <form action="ViewTransactionsServlet" method="post">
            <input type="hidden" name="account_no" value="<%= accountNo %>">
            <input type="submit" value="View Transactions">
        </form>
        <form action="DeleteAccountServlet" method="post">
            	<input type="hidden" name="account_no" value="<%= accountNo %>">
            	<input type="submit" value="Delete Account">
        </form>
        
    </div>
</body>
</html>
<%
    } else {
        response.getWriter().println("No customer details found for account number: " + accountNo);
    }
} catch (Exception e) {
    e.printStackTrace();
    response.getWriter().println("Error: " + e.getMessage());
} finally {
    try {
        if (rs != null) rs.close();
        if (stmt != null) stmt.close();
        if (conn != null) conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
%>
