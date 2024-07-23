package com.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.control.Customer;

public class DatabaseConnection {
	private String jdbcURL = "jdbc:mysql://localhost:3306/mydb2";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Nitin@123";

    private static final String SELECT_ALL_CUSTOMERS = "SELECT * FROM customers";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMERS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String fullName = rs.getString("full_name");
                String address = rs.getString("address");
                String mobileNo = rs.getString("mobile_no");
                String email = rs.getString("email");
                String accountType = rs.getString("account_type");
                double initialBalance = rs.getDouble("initial_balance");
                java.util.Date dob = rs.getDate("dob");
                String idProof = rs.getString("id_proof");

                Customer customer = new Customer(id, fullName, address, mobileNo, email,
                        accountType, initialBalance, dob, idProof);
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
