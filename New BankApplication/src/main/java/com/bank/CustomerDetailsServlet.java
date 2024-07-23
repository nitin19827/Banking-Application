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
 * Servlet implementation class CustomerDetailsServlet
 */
@WebServlet("/CustomerDetailsServlet")
public class CustomerDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNo = request.getParameter("accountno");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb2", "root", "Nitin@123");

            PreparedStatement pst = conn.prepareStatement("SELECT * FROM customers WHERE account_no = ?");
            pst.setString(1, accountNo);
            
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                out.println("<html><body>");
                out.println("<h2>Customer Details</h2>");
                out.println("<table border='1'>");
                out.println("<tr><th>Account Number</th><td>" + rs.getString("account_no") + "</td></tr>");
                out.println("<tr><th>Full Name</th><td>" + rs.getString("full_name") + "</td></tr>");
                out.println("<tr><th>Address</th><td>" + rs.getString("address") + "</td></tr>");
                out.println("<tr><th>Mobile Number</th><td>" + rs.getString("mobile_no") + "</td></tr>");
                out.println("<tr><th>Email</th><td>" + rs.getString("email") + "</td></tr>");
                out.println("<tr><th>Account Type</th><td>" + rs.getString("account_type") + "</td></tr>");
                out.println("<tr><th>Initial Balance</th><td>" + rs.getDouble("initial_balance") + "</td></tr>");
                out.println("<tr><th>Date of Birth</th><td>" + rs.getDate("dob") + "</td></tr>");
                out.println("<tr><th>ID Proof</th><td>" + rs.getString("id_proof") + "</td></tr>");
                out.println("</table>");
                out.println("</body></html>");
            } else {
                out.println("<html><body>");
                out.println("<h2>Customer not found</h2>");
                out.println("</body></html>");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
}
