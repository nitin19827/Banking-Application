package com.bank;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DepositeServlet
 */
@WebServlet("/DepositeServlet")
public class DepositeServlet extends HttpServlet {
	   private static final long serialVersionUID = 1L;

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String accountNo = request.getParameter("account_no");
	        BigDecimal depositAmount = new BigDecimal(request.getParameter("deposit_amount"));

	        Connection conn = null;
	        PreparedStatement updateStmt = null;
	        PreparedStatement insertStmt = null;

	        String url = "jdbc:mysql://localhost:3306/mydb2";
	        String user = "root";
	        String password = "Nitin@123";

	        String updateQuery = "UPDATE customers SET initial_balance = initial_balance + ? WHERE account_no = ?";
	        String insertQuery = "INSERT INTO Transactions (account_no, transaction_type, amount) VALUES (?, 'DEPOSIT', ?)";

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            conn = DriverManager.getConnection(url, user, password);
	            conn.setAutoCommit(false);

	            // Update balance
	            updateStmt = conn.prepareStatement(updateQuery);
	            updateStmt.setBigDecimal(1, depositAmount);
	            updateStmt.setString(2, accountNo);
	            updateStmt.executeUpdate();

	            // Insert transaction record
	            insertStmt = conn.prepareStatement(insertQuery);
	            insertStmt.setString(1, accountNo);
	            insertStmt.setBigDecimal(2, depositAmount);
	            insertStmt.executeUpdate();

	            conn.commit();
	            response.sendRedirect("CustomerDashboard.jsp");
	        } catch (Exception e) {
	            e.printStackTrace();
	            if (conn != null) {
	                try {
	                    conn.rollback();
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                }
	            }
	            response.getWriter().println("Error: " + e.getMessage());
	        } finally {
	            try {
	                if (updateStmt != null) updateStmt.close();
	                if (insertStmt != null) insertStmt.close();
	                if (conn != null) conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

}

