package model;

import java.sql.*;

public class Unit {
	
  //A common method to connect to the DB
  private Connection connect()
  {
    Connection con = null;
    try
      {
        Class.forName("com.mysql.jdbc.Driver");
        //Provide the correct details: DBServer/DBName, username, password
        con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ElectroGrid?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "Root123.");
        
      //For testing          
		 System.out.print(" " +" Successfully connected");
      }
    catch (Exception e)
      {
        e.printStackTrace();
      }
      return con;
  }
 
  
  //GET part
  public String readUnits()
  {
    String output = "";
    
      try
      {
        Connection con = connect();
        if (con == null) {  
        return "Error while connecting to the database for reading."; 
        }
        
        // Prepare the html table to be displayed
        output = "<table align='center' class='table'><tr align='center'>"
        		+ "<th> District </th>"
        		+ "<th>District NO</th><th> Unit Price/Unit </th>"
        		+"<th> Update </th><th> Delete </th>";
        
        String query = "SELECT * FROM units";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
    
       // iterate through the rows in the result set
      
      while (rs.next())
      {
        String unitID = Integer.toString(rs.getInt("unitID"));
        String dist = rs.getString("dist");
        String distID = Integer.toString(rs.getInt("distID"));
        String unitp = rs.getString("unitp");
    
       
        // Add into the html table
        output += "<tr align='center'><td><input id='hidUnitIDUpdate' name='hidUnitIDUpdate' type='hidden' value='" + unitID + "'>" + dist + "</td>";
        output += "<td>" + distID + "</td>";
        output += "<td>" + unitp + "</td>";
        
        // buttons     
		output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-primary'></td>"
		        + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-unitid='"+ unitID +"'>"+"</td></tr>";
       
      }
        con.close();
        // Complete the html table
        output += "</table>";
      }
      catch (Exception e)
      {
        output = "Error while reading the units.";
        System.err.println(e.getMessage());
      }
    return output;
  }
  
  
	//POST part
  public String insertUnit(String dist, String distID, String unitp)
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
      String query = " INSERT INTO units(`unitID`,`dist`,`distID`,`unitp`)"+ " values (?,?,?,?)";
      PreparedStatement preparedStmt = con.prepareStatement(query);
         
        // binding values
      preparedStmt.setInt(1, 0);
      preparedStmt.setString(2, dist);
      preparedStmt.setString(3, distID);
      preparedStmt.setString(4, unitp);
      
      preparedStmt.execute();
      con.close();
    //create JSON Object
	  String newUnit = readUnits();
	  output = "{\"status\":\"success\", \"data\": \"" + newUnit + "\"}";
    }
    catch (Exception e)
    {
      output = "{\"status\":\"error\", \"data\": \"Error while inserting the Unit.\"}";
      System.err.println(e.getMessage());
    }
    return output;
  }
  
  
	//PUT part
  public String updateUnit(String unitID, String dist,String distID,String unitp)
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
          String query = "UPDATE units SET dist=?,distID=?,unitp=? WHERE unitID=?";
          PreparedStatement preparedStmt = con.prepareStatement(query);
        
        // binding values
        preparedStmt.setString(1, dist);
        preparedStmt.setInt(2, Integer.parseInt(distID));
        preparedStmt.setString(3, unitp);
        preparedStmt.setInt(4, Integer.parseInt(unitID));
        
        preparedStmt.execute();
        con.close();
        
        
      //create JSON Object
		  String newUnit = readUnits();
		  output = "{\"status\":\"success\", \"data\": \"" + newUnit + "\"}";
      }
      catch (Exception e)
      {
        output = "{\"status\":\"error\", \"data\": \"Error while updating the Unit.\"}";
        System.err.println(e.getMessage());
      }
    return output;
  }
    
  
  
//DELETE part 
  public String deleteUnit(String unitID)
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
          String query = "DELETE FROM units WHERE unitID=?";
          PreparedStatement preparedStmt = con.prepareStatement(query);
      
      // binding values
          preparedStmt.setInt(1, Integer.parseInt(unitID));
      
      // execute the statement
          preparedStmt.execute();
          con.close();          
          
        //create JSON Object
		  String newUnit = readUnits();
		  output = "{\"status\":\"success\", \"data\": \"" + newUnit + "\"}";
      }
      catch (Exception e)
      {
          output = "{\"status\":\"error\", \"data\": \"Error while deleting the Unit.\"}";
          System.err.println(e.getMessage());
      }
      return output;
      }
  }