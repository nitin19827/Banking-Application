package com.bank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CustomerDashboardServlet
 */
@WebServlet("/CustomerDashboardServlet")
public class CustomerDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("account_no") == null) {
            response.sendRedirect("CustomerLogin.jsp");
            return;
        }

        String accountNo = (String) session.getAttribute("account_no");

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://localhost:3306/mydb2";
        String user = "root";
        String password = "Nitin@123";

        String query = "SELECT full_name, address, mobile_no, email_id, account_type, balance, dob, id_proof FROM customers WHERE account_no = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.prepareStatement(query);
            stmt.setString(1, accountNo);
            rs = stmt.executeQuery();

            if (rs.next()) {
                request.setAttribute("full_name", rs.getString("full_name"));
                request.setAttribute("address", rs.getString("address"));
                request.setAttribute("mobile_no", rs.getString("mobile_no"));
                request.setAttribute("email_id", rs.getString("email_id"));
                request.setAttribute("account_type", rs.getString("account_type"));
                request.setAttribute("balance", rs.getBigDecimal("balance"));
                request.setAttribute("dob", rs.getDate("dob"));
                request.setAttribute("id_proof", rs.getString("id_proof"));

                request.getRequestDispatcher("CustomerDashboard.jsp").forward(request, response);
            } else {
                response.getWriter().println("No customer details found.");
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
    }

}

