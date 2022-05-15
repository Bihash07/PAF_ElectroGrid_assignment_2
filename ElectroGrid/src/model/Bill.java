package model;

import java.sql.*;

public class Bill {
	
	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		try
			{
				Class.forName("com.mysql.jdbc.Driver");
				//Provide the correct details: DBServer/DBName, username, password
		        con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/ElectroGrid", "root", "");
		        
				//For testing          
				 System.out.print("Successfully connected");
			}
		catch (Exception e)
			{
				e.printStackTrace();
			}
			return con;
	}
	
	
	//GET part
		public String readBills()
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
				  output = "<table class='table'><tr align='center'>"
				  		+ "<th>User Name</th>"
				  		+ "<th>District NO</th><th>Date</th>"
				  		+ "<th>Reading</th><th>Bill Amount</th>"
				  		+"<th>Update</th><th>Delete</th>";
				  
				  String query = "SELECT * FROM bills";
//				  String query = "SELECT  b.billID,b.uname,b.distID,b.date,b.reading,(b.reading * u.unitp)total FROM bills b ,units u WHERE b.distID=u.distID ";
				  Statement stmt = con.createStatement();
				  ResultSet rs = stmt.executeQuery(query);
			
			   // iterate through the rows in the result set
			  
			  while (rs.next())
			  {
					String billID = Integer.toString(rs.getInt("b.billID"));
					String uname = rs.getString("b.uname");
					String distID = Integer.toString(rs.getInt("b.distID"));
					String date = rs.getString("b.date");
					String reading = Double.toString(rs.getDouble("b.reading"));
					String total = rs.getString("total");
				
					// Add into the html table
					output += "<tr align='center'><td><input id='hidUnitIDUpdate' name='hidUnitIDUpdate' type='hidden' value='" + billID + "'>" + uname + "</td>";
					output += "<td>" + distID + "</td>";
					output += "<td>" + date + "</td>";
					output += "<td>" + reading + " units" +"</td>";
					output += "<td>" +"Rs."+ total + "</td>";
					
					
					 // buttons     
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-primary'></td>"
					        + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-billid='"+ billID +"'>"+"</td></tr>";
			       
				
			  }
					con.close();
					// Complete the html table
					output += "</table>";
			  }
			  catch (Exception e)
			  {
					output = "Error while reading the bills.";
					System.err.println(e.getMessage());
			  }
			return output;
		}
		
	//POST method
	public String insertBill(String uname, String distID, String date, String reading)
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
			String query = " insert into bills(`billid`,`uname`,`distid`,`date`,`reading`)"+ " values (?,?,?,?,?)";
	        PreparedStatement preparedStmt = con.prepareStatement(query);
	       
	      // binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, uname);
			preparedStmt.setString(3, distID);
			preparedStmt.setString(4, date);
			preparedStmt.setDouble(5, Double.parseDouble(reading));
	
	
		  // execute the statement
			preparedStmt.execute();
			con.close();
			//create JSON Object
			  String newBill = readBills();
			  output = "{\"status\":\"success\", \"data\": \"" + newBill + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the Bill.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	public String updateBill(String billID, String uname, String distID, String date, String reading)
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
				  String query = "UPDATE bills SET uname=?,distID=?,date=?,reading=? WHERE billID=?";
				  PreparedStatement preparedStmt = con.prepareStatement(query);
			  
			  // binding values
				preparedStmt.setString(1, uname);
				preparedStmt.setInt(2, Integer.parseInt(distID));
				preparedStmt.setString(3, date);
				preparedStmt.setDouble(4, Double.parseDouble(reading));
				preparedStmt.setInt(5, Integer.parseInt(billID));
			 
			  // execute the statement
				preparedStmt.execute();
				con.close();
				
				//create JSON Object
				  String newBill = readBills();
				  output = "{\"status\":\"success\", \"data\": \"" + newBill + "\"}";
				
		  }
		  catch (Exception e)
		  {
				output = "{\"status\":\"error\", \"data\": \"Error while updating the Bill.\"}";
				System.err.println(e.getMessage());
		  }
		return output;
	}
			
	public String deleteBill(String billID)
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
			  	String query = "DELETE FROM bills WHERE billID=?";
			  	PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			  	preparedStmt.setInt(1, Integer.parseInt(billID));
			
			// execute the statement
			  	preparedStmt.execute();
			  	con.close();
			
			//create JSON Object
				  String newBill = readBills();
				  output = "{\"status\":\"success\", \"data\": \"" + newBill + "\"}";
		      }
		      catch (Exception e)
		      {
		          output = "{\"status\":\"error\", \"data\": \"Error while deleting the Bill.\"}";
		          System.err.println(e.getMessage());
		      }
		      return output;
		      }
		  }
