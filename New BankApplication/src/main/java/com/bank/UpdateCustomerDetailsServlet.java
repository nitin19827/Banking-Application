package com.bank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateCustomerDetailsServlet
 */
@WebServlet("/UpdateCustomerDetailsServlet")
public class UpdateCustomerDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNo = request.getParameter("accountno");

        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb2", "root", "Nitin@123");

            pst = conn.prepareStatement("SELECT * FROM customers WHERE account_no = ?");
            pst.setString(1, accountNo);
            rs = pst.executeQuery();

            if (rs.next()) {
                Map<String, String> customer = new HashMap<>();
                customer.put("account_no", rs.getString("account_no"));
                customer.put("full_name", rs.getString("full_name"));
                customer.put("address", rs.getString("address"));
                customer.put("mobile_no", rs.getString("mobile_no"));
                customer.put("email", rs.getString("email"));
                customer.put("account_type", rs.getString("account_type"));
                customer.put("id_proof", rs.getString("id_proof"));

                request.setAttribute("customer", customer);
                RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateCustomerForm.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("message", "No customer found with Account Number " + accountNo + ".");
                RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateCustomer.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNo = request.getParameter("accountno");
        String fullName = request.getParameter("fullname");
        String address = request.getParameter("address");
        String mobileNo = request.getParameter("mobileno");
        String email = request.getParameter("email");
        String accountType = request.getParameter("accountType");
        String idProof = request.getParameter("idProof");

        String message = "";

        Connection conn = null;
        PreparedStatement pst = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb1", "root", "Nitin@123");

            pst = conn.prepareStatement("UPDATE customers SET full_name = ?, address = ?, mobile_no = ?, email = ?, account_type = ?, id_proof = ? WHERE account_no = ?");
            pst.setString(1, fullName);
            pst.setString(2, address);
            pst.setString(3, mobileNo);
            pst.setString(4, email);
            pst.setString(5, accountType);
            pst.setString(6, idProof);
            pst.setString(7, accountNo);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                message = "Customer with Account Number " + accountNo + " has been successfully updated.";
            } else {
                message = "No customer found with Account Number " + accountNo + ".";
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            message = "An error occurred while attempting to update the customer.";
        } finally {
            try {
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateCustomer.jsp");
        dispatcher.forward(request, response);
    }

}
