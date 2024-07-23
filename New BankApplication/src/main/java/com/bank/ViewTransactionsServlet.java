package com.bank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewTransactionsServlet")
public class ViewTransactionsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNo = request.getParameter("account_no");

        List<String> transactions = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb2", "root", "Nitin@123");
            pst = conn.prepareStatement("SELECT * FROM Transactions WHERE account_no = ?");
            pst.setString(1, accountNo);
            rs = pst.executeQuery();

            while (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("transaction_time");
                String type = rs.getString("transaction_type");
                double amount = rs.getDouble("amount");

                String transactionInfo = "Timestamp: " + timestamp + ", Type: " + type + ", Amount: " + amount;
                transactions.add(transactionInfo);
            }

            // Display transactions or pass to JSP for display
            // For example, set as request attribute and forward to JSP
            request.setAttribute("transactions", transactions);
            request.getRequestDispatcher("viewTransactions.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }        
}
