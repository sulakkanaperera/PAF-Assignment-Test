package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Doctor {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctor?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertDoctor(String name, String nic, String price, String phone)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for inserting."; } 
	 
			// create a prepared statement    
			String query = " insert into doctor1(`DocID`,`Dname`,`Nic`,`DoctorPrice`,`phone`)" + " values (?, ?, ?, ?, ?)"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, 0);    
			preparedStmt.setString(2, name);    
			preparedStmt.setString(3, nic);    
			preparedStmt.setDouble(4, Double.parseDouble(price));     
			preparedStmt.setString(5, phone);
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newDoctors = readDoctors(); 
			output =  "{\"status\":\"success\", \"data\": \"" +        
							newDoctors + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the doctor.\"}";  
			System.err.println(e.getMessage());   
		} 
		
	  return output;  
	} 
	
	public String readDoctors()  
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
			output = "<table border=\'1\'><tr><th>Doctor name</th><th>Nic</th><th>Doctor Price</th><th>Phone</th><th>Update</th><th>Remove</th></tr>"; 
	 
			String query = "select * from doctor1";    
			Statement stmt = con.createStatement();    
			ResultSet rs = stmt.executeQuery(query); 
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				String DocID = Integer.toString(rs.getInt("DocID"));     
				String Dname = rs.getString("Dname");     
				String Nic = rs.getString("Nic");     
				String DoctorPrice = Double.toString(rs.getDouble("DoctorPrice"));    
				String Phone = rs.getString("Phone"); 
			
	 
				// Add into the html table 
				output += "<tr><td><input id=\'hidDocIDUpdate\' name=\'hidDocIDUpdate\' type=\'hidden\' value=\'" + DocID + "'>" 
							+ Dname + "</td>";      
				output += "<td>" + Nic + "</td>";     
				output += "<td>" + DoctorPrice + "</td>";     
				output += "<td>" + Phone + "</td>"; 
	 
				// buttons     
//				output += "<td><input name=\'btnUpdate\' type=\'button\' value=\'Update\' class=\' btnUpdate btn btn-secondary\'></td>"
//						//+ "<td><form method=\"post\" action=\"Doctors.jsp\">      "
//						+ "<input name=\'btnRemove\' type=\'submit\' value=\'Remove\' class=\'btn btn-danger\'> "
//						+ "<input name=\"hidDocIDDelete\" type=\"hidden\" value=\"" + DocID + "\">" + "</form></td></tr>"; 
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
							+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-Docid='" + DocID + "'>" + "</td></tr>"; 
			} 
	 
			con.close(); 
	 
			// Complete the html table    
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the doctors.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	 
	
	public String updateDoctor(String ID, String name, String nic, String price, String phone)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for updating."; } 
	 
			// create a prepared statement    
			String query = "UPDATE doctor1 SET Dname=?,Nic=?,DoctorPrice=?,Phone=? WHERE DocID=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setString(1, name);    
			preparedStmt.setString(2, nic);    
			preparedStmt.setDouble(3, Double.parseDouble(price));     
			preparedStmt.setString(4, phone);    
			preparedStmt.setInt(5, Integer.parseInt(ID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newDoctors = readDoctors();    
			output = "{\"status\":\"success\", \"data\": \"" +        
									newDoctors + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the doctor.\"}";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	} 
	
	public String deleteDoctor(String DocID)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for deleting."; } 
	 
			// create a prepared statement    
			String query = "delete from doctor1 where DocID=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(DocID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newDoctors = readDoctors();    
			output = "{\"status\":\"success\", \"data\": \"" +       
								newDoctors + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the item.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	 
	 
}



