package com.bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteAccountServlet
 */
@WebServlet("/DeleteAccountServlet")
public class DeleteAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNo = request.getParameter("account_no");

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb2", "root", "Nitin@123");

            // Check account balance
            String balanceQuery = "SELECT initial_balance, full_name FROM customers WHERE account_no = ?";
            stmt = conn.prepareStatement(balanceQuery);
            stmt.setString(1, accountNo);
            rs = stmt.executeQuery();

            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();

            if (rs.next()) {
                double balance = rs.getDouble("initial_balance");
                String fullName = rs.getString("full_name");

                if (balance > 1000) {
                    out.println("<html><body>");
                    out.println("<h2>Account Number " + accountNo + " (Account Holder: " + fullName + ") cannot be deleted.</h2>");
                    out.println("<h3>Remaining balance: $" + balance + "</h3>");
                    out.println("<a href='CustomerDashboard.jsp'>Return to Dashboard</a>");
                    out.println("</body></html>");
                } else {
                    // Delete associated transactions first
                    String deleteTransactionsQuery = "DELETE FROM transactions WHERE account_no = ?";
                    stmt = conn.prepareStatement(deleteTransactionsQuery);
                    stmt.setString(1, accountNo);
                    stmt.executeUpdate();

                    // Proceed to delete the account
                    String deleteQuery = "DELETE FROM customers WHERE account_no = ?";
                    stmt = conn.prepareStatement(deleteQuery);
                    stmt.setString(1, accountNo);
                    int rowsAffected = stmt.executeUpdate();

                    if (rowsAffected > 0) {
                        out.println("<html><body>");
                        out.println("<h2>Customer with Account Number " + accountNo + " has been successfully deleted.</h2>");
                        out.println("<a href='index.jsp'>Return to Home</a>");
                        out.println("</body></html>");
                    } else {
                        out.println("<html><body>");
                        out.println("<h2>No customer found with Account Number " + accountNo + ".</h2>");
                        out.println("<a href='CustomerDashboard.jsp'>Return to Dashboard</a>");
                        out.println("</body></html>");
                    }
                }
            } else {
                out.println("<html><body>");
                out.println("<h2>No customer found with Account Number " + accountNo + ".</h2>");
                out.println("<a href='CustomerDashboard.jsp'>Return to Dashboard</a>");
                out.println("</body></html>");
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


