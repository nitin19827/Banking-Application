package com.bank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CustomerPasswordChangeServlet")
public class CustomerPasswordChangeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNo = request.getParameter("account_no");
        String newPassword = request.getParameter("new_password");

        // Debugging statement
        System.out.println("Account Number: " + accountNo);

        if (accountNo == null || newPassword == null || accountNo.isEmpty() || newPassword.isEmpty()) {
            response.getWriter().println("Error: Account number or password cannot be empty.");
            return;
        }

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb2", "root", "Nitin@123");

            // Check if account exists
            String checkQuery = "SELECT account_no FROM customers WHERE account_no = ?";
            stmt = conn.prepareStatement(checkQuery);
            stmt.setString(1, accountNo);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // Account exists, proceed with update
                String updateQuery = "UPDATE customers SET password = ?, first_login = 0 WHERE account_no = ?";
                stmt = conn.prepareStatement(updateQuery);
                stmt.setString(1, newPassword); // Make sure to hash the password before storing it
                stmt.setString(2, accountNo);

                int rowsUpdated = stmt.executeUpdate();

                if (rowsUpdated > 0) {
                    response.getWriter().println("Password changed successfully. You can now login with your new password.");
                    response.sendRedirect("CustomerLogin.jsp");
                } else {
                    response.getWriter().println("Error: Failed to update the password.");
                }
            } else {
                // Account does not exist
                response.getWriter().println("Error: Account not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
