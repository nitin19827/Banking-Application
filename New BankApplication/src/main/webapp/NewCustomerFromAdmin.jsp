<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Customer</title>
<style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding: 20px;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
        }
        form {
            display: flex;
            flex-direction: column;
            gap: 10px;
        }
        label {
            font-weight: bold;
        }
        input, select {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            padding: 10px;
            background-color: #007BFF;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<body>
   <div class="container">
        <h1>New Customer Registration Form</h1>
        <form action="<%=request.getContextPath()%>/CustomerServlet" method="post">
            <label for="fullName">Full Name:</label>
            <input type="text" id="fullName" name="fullName" required>
            
            <label for="address">Address:</label>
            <input type="text" id="address" name="address" required>
            
            <label for="mobileNo">Mobile No:</label>
            <input type="text" id="mobileNo" name="mobileNo" required>
            
            <label for="email">Email ID:</label>
            <input type="email" id="email" name="email" required>
            
            <label for="accountType">Type of Account:</label>
            <select id="accountType" name="accountType" required>
                <option value="Saving">Saving Account</option>
                <option value="Current">Current Account</option>
            </select>
            
            <label for="initialBalance">Initial Balance (min 1000):</label>
            <input type="number" id="initialBalance" name="initialBalance" min="1000" required>
            
            <label for="dob">Date of Birth:</label>
            <input type="date" id="dob" name="dob" required>
            
            <label for="idProof">ID Proof:</label>
            <input type="text" id="idProof" name="idProof" required>
            
            <button type="submit">Register Customer</button>
        </form>
    </div>
</body>
</body>
</html>