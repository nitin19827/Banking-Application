<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.List, java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transactions</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f8f9fa; }
        h1 { color: #007bff; }
        ul { list-style-type: none; padding: 0; }
        li { padding: 10px; border-bottom: 1px solid #ddd; }
    </style>
</head>
<body>
    <h1>Transactions</h1>
    <ul>
        <% 
            // Retrieve transactions from request attribute
            List<String> transactions = (List<String>) request.getAttribute("transactions");
            
            // Iterate over transactions and display
            if (transactions != null) {
                for (String transaction : transactions) {
        %>
                    <li><%= transaction %></li>
        <%
                }
            } else {
        %>
                <li>No transactions found.</li>
        <% 
            }
        %>
         <!-- Download button for PDF -->
    <form action="DownloadTransactionsServlet" method="post">
        <input type="hidden" name="account_no" value="<%= request.getParameter("account_no") %>">
        <button type="submit" class="download-btn">Download as PDF</button>
    </form>
    </ul>
</body>
</html>
