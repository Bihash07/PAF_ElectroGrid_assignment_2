package model;

import java.sql.*;

//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;

public class Fb {
	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		try
			{
				Class.forName("com.mysql.jdbc.Driver");
				//Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/lab", "root", "Root123.");
				
				System.out.print("Successfully connected");
			}
		catch (Exception e)
			{
				e.printStackTrace();
			}
			return con;
	}
	
	public String insertItem(String fName, String eMail, String feedBack)
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
			String query = "INSERT INTO feed(`fID`,`fName`,`eMail`,`feedBack`)"+ " values (?, ?, ?, ?)";
	        PreparedStatement preparedStmt = con.prepareStatement(query);
	       
	      // binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, fName);
			preparedStmt.setString(3, eMail);
			preparedStmt.setString(4, feedBack);
	
	
		  // execute the statement
			preparedStmt.execute();
			con.close();
		
			String newFb = readItems();
			output = "{\"status\":\"success\", \"data\": \"" + newFb + "\"}";
			
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the FeedBack.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String readItems()
	{
		String output = "";
		  try
		  {
			  Connection con = connect();
			  if (con == null)
		  {	
			  return "Error while connecting to the database for feedBack."; 
	      }
			  
		  	// Prepare the html table to be displayed
			  output = "<table class='table'><tr align='center'><th>User Name</th><th>e-mail Address</th>" +
					  "<th>FeedBack</th><th> Update</th><th>Delete</th>" 
					  
					 ;
			  String query = "select * from feed";
			  Statement stmt = con.createStatement();
			  ResultSet rs = stmt.executeQuery(query);
		
		   // iterate through the rows in the result set
		  
		  while (rs.next())
		  {
				String fID = Integer.toString(rs.getInt("fID"));
				String fName = rs.getString("fName");
				String eMail = rs.getString("eMail");
				String feedBack = rs.getString("feedBack");
			
				// Add into the html table
				 output += "<tr align='center'><td><input id='hidfIDUpdate' name='hidfIDUpdate' type='hidden' value='" + fID + "'>" + fName + "</td>";
			     output += "<td>" + eMail + "</td>";
			     output += "<td>" + feedBack + "</td>";
				 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-fID='"+ fID +"'>"+"</td></tr>";
				       
		
			
		  }
				con.close();
				// Complete the html table
				output += "</table>";
		  }
		  catch (Exception e)
		  {
				output = "Error while feedBack the feed.";
				System.err.println(e.getMessage());
		  }
		return output;
	}
	
	public String updateItem(String fID, String fName, String eMail, String feedBack)
	{
		String output = "";
		  try
		  {
			  Connection con = connect();
			  if (con == null)
		  {
			  return "Error while connecting to the database for updating."; 
		  }
			  
			  // create a prepared statement
				  String query = "UPDATE feed SET fName=?,eMail=?,feedBack=? WHERE fID=?";
				  PreparedStatement preparedStmt = con.prepareStatement(query);
			  
			  // binding values
				preparedStmt.setString(0, fName);
				preparedStmt.setString(1, eMail);
				preparedStmt.setString(2, feedBack);
				preparedStmt.setInt(3, Integer.parseInt(fID));
			 
			  // execute the statement
				preparedStmt.execute();
				con.close();
				
				 //create JSON Object
				  String newfb = readItems();
				  output = "{\"status\":\"success\", \"data\": \"" + newfb + "\"}";
		      
		  }
		  catch (Exception e)
		  {
			  output = "{\"status\":\"error\", \"data\": \"Error while updating the FeedBack.\"}";
			  System.err.println(e.getMessage());
		  }
		return output;
	}
			
	public String deleteItem(String fID)
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
			  	String query = "delete from feed where fID=?";
			  	PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			  	preparedStmt.setInt(1, Integer.parseInt(fID));
			
			// execute the statement
			  	preparedStmt.execute();
			  	con.close();
			  //create JSON Object
				  String newfb = readItems();
				  output = "{\"status\":\"success\", \"data\": \"" + newfb + "\"}";
		  }
		  catch (Exception e)
		  {
			  	output = "Error while deleting the FeedBack.";
			  	System.err.println(e.getMessage());
		  }
		  return output;
			}
	}
