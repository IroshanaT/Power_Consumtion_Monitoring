package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Monitor {
	
	private Connection connect() { 
		Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/power_plant", "root", ""); 
	 } 
	    catch (Exception e) {
	    	
	    	System.out.print("Connection Failed");
	    	e.printStackTrace();
	    	System.out.print(e);
	    } 
	 
	    return con; 
	 } 
	
	public String insertPowerPlant (String areaId, String pName, String pLocation, String type, String status, String capacity) 
	{ 
	  String output = ""; 

	try
	{ 
		 
	   Connection con = connect(); 
	   
	   if (con == null) 
	   {return "Error while connecting to the database for inserting."; } 

	   // create a prepared statement
	   String query = " INSERT INTO plant_db VALUES (?, ?, ?, ?, ?, ?, ?)"; 

	   PreparedStatement preparedStmt = con.prepareStatement(query); 

	   // binding values
	   preparedStmt.setInt(1, 0); 
	   preparedStmt.setString(2, areaId); 
	   preparedStmt.setString(3, pName); 
	   preparedStmt.setString(4, pLocation);
	   preparedStmt.setString(5, type);
	   preparedStmt.setInt(6,Integer.parseInt(capacity));
	   preparedStmt.setString(7, status);
	   

	   // execute the statement
	   preparedStmt.execute(); 
	   con.close(); 
	    String newPowerPlant = readPowerPlant();
		 output =  "{\"status\":\"success\", \"data\": \"" + 
				 newPowerPlant + "\"}"; 
		 } 

		catch (Exception e) 
		 { 
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the details.\"}";  
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
		}

	public String readPowerPlant() 
	{ 
	   String output = ""; 
	   
	   try
	  { 
	     Connection con = connect(); 
	 
	     if (con == null) 
	     {return "Error while connecting to the database for reading."; } 
	 
	     // Prepare the HTML table to be displayed
	     output = "<table border='1'><tr><th>Area ID</th>" +
	              "<th>Power Plant Name</th>" + 
	              "<th>Location</th>" +
	              "<th>Power Plant Type</th>" +
	              "<th>Capacity(MWh)</th>" +
	              "<th>Status</th>" +
	              "<th>Update</th><th>Remove</th></tr>"; 
	 
	   String query = "select * from plant_db"; 
	   Statement stmt = con.createStatement(); 
	   ResultSet rs = stmt.executeQuery(query); 
	 
	   // iterate through the rows in the result set
	  while (rs.next()) 
	 { 
	      String powerpId = Integer.toString(rs.getInt("powerpId")); 
	      String areaId = rs.getString("areaId"); 
	      String pName = rs.getString("pName"); 
	      String pLocation = rs.getString("pLocation");
	      String type = rs.getString("type");
	      String capacity = Integer.toString(rs.getInt("capacity"));
	      String status = rs.getString("status");
	      
	 
	   // Add a row into the HTML table
			 output += "<tr><td><input id='hidpowerpIdUpdate' name='hidpowerpIdUpdate' type='hidden' value='" + powerpId + "'>"
					 + areaId + "</td>";
			 output += "<td>" + pName + "</td>"; 
			 output += "<td>" + pLocation + "</td>"; 
			 output += "<td>" + type + "</td>";
			 output += "<td>" + capacity + "</td>";
			 output += "<td>" + status + "</td>";
			 
			
	   // buttons
			 output += "<td><input name='btnUpdate' " 
			 + " type='button' value='Update' class =' btnUpdate btn btn-secondary'data-powerpId='" + powerpId + "'></td>"
			 + "<td><form method='post' action='index.jsp'>"
			 + "<input name='btnRemove' " 
			 + " type='button' value='Remove' class='btnRemove btn btn-danger' data-powerpId='" + powerpId + "'>"
			 + "<input name='hidpowerpIdDelete' type='hidden' " 
			 + " value='" + powerpId + "'>" + "</form></td></tr>"; 
			 } 
			 con.close(); 
			 // Complete the HTML table
			 output += "</table>"; 
			 } 
			catch (Exception e) 
			 { 
			 output = "Error while reading the users."; 
			 System.err.println(e.getMessage()); 
			 } 
			return output; 
		}

	public String updatePowerPlant(String powerpId ,String pName, String areaId, String pLocation, String type,  String status, String capacity)
	 { 
	   
		String output = ""; 
	 
		try
	   { 
	      Connection con = connect(); 
	 
	      if (con == null) 
	      {return "Error while connecting to the database for updating."; } 
	 
	     // create a prepared statement
	     String query = "UPDATE plant_db SET areaId=?,pName=?,pLocation=?,type=?,status=?  WHERE powerpId=?"; 
	     PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	 
	     // binding values
	    preparedStmt.setString(1, areaId); 
	    preparedStmt.setString(2, pName); 
	    preparedStmt.setString(3, pLocation);
	    preparedStmt.setString(4, type);
	    preparedStmt.setString(5, status);
	    preparedStmt.setInt(6,Integer.parseInt (capacity));
	    preparedStmt.setInt(7, Integer.parseInt(powerpId)); 
	 
	    // execute the statement
	    preparedStmt.execute(); 
	    con.close();
		String newPowerPlant = readPowerPlant();
		 output =  "{\"status\":\"success\", \"data\": \"" + 
				 newPowerPlant + "\"}"; 
		 } 

		catch (Exception e) 
		 { 
			output = "{\"status\":\"error\", \"data\": \"Error while Updating the power palnt.\"}";  
		
		System.err.println(e.getMessage());
		}
		return output;
		}


	public String deletePowerPlant(String powerpId) 
	 { 
	 String output = ""; 
	 
	 try
	 { 
	    Connection con = connect(); 
	    if (con == null) 
	    {return "Error while connecting to the database for deleting."; } 
	 
	    // create a prepared statement
	    String query = "delete from plant_db where powerpId=?"; 
	    PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	    // binding values
	    preparedStmt.setInt(1, Integer.parseInt(powerpId)); 
	 
	 // execute the statement
	 	 preparedStmt.execute(); 
	 	 con.close(); 
	 	 String newPowerPlant  = readPowerPlant();
	 	 output =  "{\"status\":\"success\", \"data\": \"" + 
	 			newPowerPlant + "\"}"; 
	 	 } 

	 	catch (Exception e) 
	 	 { 
	 		output = "{\"status\":\"error\", \"data\": \"Error while Deleting the power plant.\"}";  
	 	 System.err.println(e.getMessage()); 
	 	 } 
	 	return output; 
	 		}


}
