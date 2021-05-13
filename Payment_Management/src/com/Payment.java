package com;

import java.sql.*;

public class Payment {

	public Connection connect()
	{
	 Connection con = null;

	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");
	 con= DriverManager.getConnection("Jdbc:mysql://localhost:3307/paymentapi", "root", "");
	 //For testing
	 System.out.print("Successfully connected");
	 }
	 catch(Exception e)
	 {
	 e.printStackTrace();
	 }

	 return con;
	}
	public String insertPayment(String paymentId,int amount, String payDate, String cardHolder, String cardNo,int cvv, String expDate)
	{
	 String output = "";
	try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database";
	 }
	 // create a prepared statement
	 String query = " INSERT INTO `payment`(`paymentID`, `Amount`, `payDate`, `cardHolder`, `cardNo`, `cvv`, `expDate`) VALUES (?,?,?,?,?,?,?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, paymentId);
	 preparedStmt.setInt(3, amount);
	 preparedStmt.setString(4, payDate);
	 preparedStmt.setString(5, cardHolder);
	 preparedStmt.setString(6, cardNo);
	 preparedStmt.setInt(7, cvv);
	 preparedStmt.setString(8, expDate);
	//execute the statement
	 preparedStmt.execute();
	 con.close();
	 String newPayment = readPayment();
	 output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";
	 }
	catch (Exception e)
	{
	 output = "{\"status\":\"error\", \"data\": \"Error while inserting payment details.\"}";
	 System.err.println(e.getMessage());
	}
	return output;
	}
	
	public String readPayment()
	{
	 String output = "";
	try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database for reading.";
	 }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Payment ID</th><th>Amount</th><th>PayDate</th><th>Card Holder</th><th>Card Number</th><th>CVV</th><th>Exp Date</th>" +
				"<th>Update</th><th>Remove</th></tr>";
	 
	 String query = "SELECT * FROM `payment`";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
		String paymentId = Integer.toString(rs.getInt("paymentID"));
		String amount = Integer.toString(rs.getInt("Amount"));
		String payDate = rs.getString("payDate");
		String cardHolder = rs.getString("cardHolder");
		String cardNo = rs.getString("cardNo");
		String cvv = Integer.toString(rs.getInt("cvv"));
		String expDate = rs.getString("expDate");
		 
	
	// Add into the html table
		output += "<tr><td>" + paymentId + "</td>";
		output += "<td>" + amount + "</td>";
		output += "<td>" + payDate + "</td>";
		output += "<td>" + cardHolder + "</td>";
		output += "<td>" + cardNo + "</td>";
		output += "<td>" + cvv + "</td>";
		output += "<td>" + expDate + "</td>";
		
	// buttons
		output += "<td><input name='btnUpdate' type='button' value='Update' "
		+ "class='btnUpdate btn btn-secondary' data-paymentid='" + paymentId + "'></td>"
		+ "<td><input name='btnRemove' type='button' value='Remove' "
		+ "class='btnRemove btn btn-danger' data-paymentid='" + paymentId + "'></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	catch (Exception e)
	 {
	 output = "Error while reading the items.";
	 System.err.println(e.getMessage());
	 }
	return output;
	}

	
	public String updatePayment(String paymentId,int amount, String payDate, String cardHolder, String cardNo,int cvv, String expDate)
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{
	return "Error while connecting to the database";
	}
	// create a prepared statement
	String query = "UPDATE `payment` SET `paymentID`='"+paymentId+"',`Amount`='"+amount+"',`payDate`='"+payDate+"',`cardHolder`='"+cardHolder+"',`cardNo`='"+cardNo+"',`cvv`='"+cvv+"',`expDate`='"+expDate+"'";

	PreparedStatement preparedStmt = con.prepareStatement(query);

	//execute the statement
	preparedStmt.execute();
	con.close();
	output = "Inserted successfully";
	}
	catch (Exception e)
	{
	output = "Error while inserting";
	System.err.println(e.getMessage());
	}
	return output;
	}

	
	public String deletePayment(String paymentId)
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{
	return "Error while connecting to the database for deleting.";
	}
	// create a prepared statement
	String query = "delete from items where itemID=?";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	preparedStmt.setInt(1, Integer.parseInt(paymentId));
	// execute the statement
	preparedStmt.execute();
	con.close();
	String newPayment = readPayment();
	output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";
	}
	catch (Exception e)
	{
	 output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
	 System.err.println(e.getMessage());
	}
	return output;
	}

	
}

