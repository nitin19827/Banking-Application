package com.bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.SQLException;


/**
 * Servlet implementation class DeleteCustomerServlet
 */
@WebServlet("/DeleteCustomerServlet")
public class DeleteCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNo = request.getParameter("accountno");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb2", "root", "Nitin@123");

            PreparedStatement pst = conn.prepareStatement("DELETE FROM customers WHERE account_no = ?");
            pst.setString(1, accountNo);
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                out.println("<html><body>");
                out.println("<h2>Customer with Account Number " + accountNo + " has been successfully deleted.</h2>");
                out.println("</body></html>");
            } else {
                out.println("<html><body>");
                out.println("<h2>No customer found with Account Number " + accountNo + ".</h2>");
                out.println("</body></html>");
            }

            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("<html><body>");
            out.println("<h2>An error occurred while attempting to delete the customer.</h2>");
            out.println("</body></html>");
        } finally {
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}