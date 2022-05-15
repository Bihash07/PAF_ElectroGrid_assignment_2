package com;
import java.sql.*;
public class Account {
	//A common method to connect to the DB
		private Connection connect()
		 {
		 Connection con = null;
		 try
		 {
		 Class.forName("com.mysql.jdbc.Driver");

		
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/lab", "root", "Root123.");
		 System.out.print("Successfully Connected");
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 return con;
		 }
		
		//Insert Query
		public String insertcustomermanagement(String cust_name, String address,String nic,String district, String mobile, String e_service, String wire_install)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for inserting."; 
		 }
		 
		 // create a prepared statement
		 String query = " insert into customer (`acc_no`,`cust_name`,`address`,`nic`, `district`, `mobile`,`e_service`,`wire_install`)" + " values (?, ?, ?, ?, ?, ?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, cust_name);
		 preparedStmt.setString(3, address);
		 preparedStmt.setString(4, nic);
		 preparedStmt.setString(5, district);
		 preparedStmt.setString(6, mobile);
		 preparedStmt.setString(7, e_service);
		 preparedStmt.setString(8, wire_install);
		
		// execute the statement
		
		 preparedStmt.execute();
		 con.close();
		 
		 String newInquiry = readInquiry();
		 
		 
		 output = "{\"status\":\"success\", \"data\": \"" + newInquiry + "\"}";
		 output = "Inserted Customer Details Successfully";
		 }
		 catch (Exception e)
		 {
		 output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
		 output = "Error while inserting the Customer Details.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
//Get Query
public String readInquiry()
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
		 output = "<table border='1'><tr><th>Customer Name</th>" +
		 "<th>Address</th>" +
		 "<th>NIC</th>"+
		 "<th>District</th>"+
		 "<th>Mobile</th>"+
		 "<th>Electricity Service</th>"+
		 "<th>Wiring Installation</th></tr>";

		 String query = "select * from customer";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 String acc_no = Integer.toString(rs.getInt("acc_no"));
		 String cust_name = rs.getString("cust_name");
		 String address = rs.getString("address");
		 String nic = rs.getString("nic");
		 String district = rs.getString("district");
		 String mobile = rs.getString("mobile");
		 String e_service = rs.getString("e_service");
		 String wire_install = rs.getString("wire_install");
		
		// Add a row into the html table
		 
		 //output += "<tr><td><input id='hidAccIDUpdate' name='hidAccIDUpdate' type='hidden' value='" + acc_no
					//+ "'>" + cust_name + "</td>";
		 output += "<tr><td>" + cust_name + "</td>";
		 output += "<td>" + address + "</td>";
		 output += "<td>" + nic + "</td>";
		 output += "<td>" + district + "</td>";
		 output += "<td>" + mobile + "</td>";	
		 output += "<td>" + e_service + "</td>";	
		 output += "<td>" + wire_install + "</td>";	
		 
		// buttons
		output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-accid='" + acc_no + "'></td>"
				//+ "<td><form method='post' action='items.jsp'>"
				+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-accid='" + acc_no + "'></td></tr>";
		 }
				 con.close();
				// Complete the html table
				 output += "</table>";
				 } 
		 catch (Exception e)
		 {
		 output = "Error while reading the Datas.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
//Update Query

public String updatecustomermanagement(String acc_no, String cust_name, String address, String nic, String district, String mobile, String e_service, String wire_install )
	{
		 String output = "";
	try
	{
		Connection con = connect();
		 if (con == null){
			 return "Error while connecting to the database for updating."; 
		 }
		 // create a prepared statement
		 String query = "UPDATE customer SET cust_name=?, address=?, nic=?, district=?, mobile=?, e_service=?,wire_install=? WHERE acc_no=?";
		 
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 
		//preparedStmt.setString(1, acc_no);
		 preparedStmt.setString(1, cust_name);
		 preparedStmt.setString(2, address);
		 preparedStmt.setString(3, nic);
		 preparedStmt.setString(4, district);
		 preparedStmt.setString(5, mobile);
		 preparedStmt.setString(6, e_service);
		 preparedStmt.setString(7, wire_install);
		 preparedStmt.setInt(8, Integer.parseInt(acc_no));
		 
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 
		 String newInquiry = readInquiry();
		 output = "{\"status\":\"success\", \"data\": \"" + newInquiry + "\"}";
		 output = "Updated Customer Details Successfully";
		 }
		 catch (Exception e)
		 {
		 output = "{\"status\":\"error\", \"data\":\"Error while updating the Customer Details..\"}";
		 output = "Error while updating the Customer Details.";
		 System.out.println(e.getMessage());
		 }
		 return output;
		 }


		
		//Delete Query
		public String deletecustomermanagement(String acc_no)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from customer where acc_no=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(acc_no));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 
		 String newInquiry = readInquiry();
		 output = "{\"status\":\"success\", \"data\": \"" + newInquiry + "\"}";
		 output = "Deleted Customer Details Successfully";
		 }
		 catch (Exception e)
		 {
			 output = "{\"status\":\"error\", \"data\":\"Error while deleting the item.\"}";
		 output = "Error while deleting the Customer Details.";
		 System.out.println(e.getMessage());
		 }
		 return output;
		 }

		} 

