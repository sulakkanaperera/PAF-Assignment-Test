<%@ page import="model.Doctor"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Doctors Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.4.1.min.js"></script> 
<script src="Components/doctors.js"></script> 
</head>
<body>
<div class="container"> 
	<div class="row">  
		<div class="col-6"> 
			<h1>DOCTORS MANAGEMENT</h1>
				<form id="formDoctor" name="formDoctor" method="post" action="doctors.jsp">  
					:Doctor Name  
					<input id="Dname" name="Dname" type="text" class="form-control form-control-sm">  
					<br> NIC:  
					<input id="Nic" name="Nic" type="text" class="form-control form-control-sm">  
					<br> Doctor price:  
					<input id="DoctorPrice" name="DoctorPrice" type="text" class="form-control form-control-sm">  
					<br> Phone Number:  
					<input id="Phone" name="Phone" type="text" class="form-control form-control-sm">  
					<br>  
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">  
					<input type="hidden" id="hidDocIDSave" name="hidDocIDSave" value=""> 
				</form>
				
				<div id="alertSuccess" class="alert alert-success">
					<%
						out.print(session.getAttribute("statusMsg"));
					%>
				</div>
				<div id="alertError" class="alert alert-danger"></div>
				
				<br>
				<div id="divDoctorsGrid">
					<%
						Doctor doctorObj = new Doctor();
						out.print(doctorObj.readDoctors());
					%>
				</div>
				
				 
			</div>
		</div>
</div>
 
</body>
</html>