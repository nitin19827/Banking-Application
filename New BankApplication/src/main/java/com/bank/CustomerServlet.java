package com.bank;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.Customer;
import com.database.CustomerDAO;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO;

    public void init() {
        customerDAO = new CustomerDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String mobileNo = request.getParameter("mobileNo");
        String email = request.getParameter("email");
        String accountType = request.getParameter("accountType");
        double initialBalance = Double.parseDouble(request.getParameter("initialBalance"));
        Date dob = Date.valueOf(request.getParameter("dob"));
        String idProof = request.getParameter("idProof");

        Customer newCustomer = new Customer(fullName, address, mobileNo, email, accountType, initialBalance, dob, idProof);
        try {
            customerDAO.insertCustomer(newCustomer);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        response.sendRedirect("AdminDashboard.jsp");
    }

}

