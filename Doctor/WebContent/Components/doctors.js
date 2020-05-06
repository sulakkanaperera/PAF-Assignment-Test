$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	}  
	$("#alertError").hide(); }); 
 
// SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 
 
	// Form validation-------------------  
	var status = validateDoctorForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 
 
	// If valid------------------------  
	var type = ($("#hidDocIDSave").val() == "") ? "POST" : "PUT"; 
	
	$.ajax( 
	{  
		url : "DoctorsAPI",  
		type : type,  
		data : $("#formDoctor").serialize(),  
		dataType : "text",  
		complete : function(response, status)  
		{   
			onDoctorSaveComplete(response.responseText, status);  
		} 
	}); 
}); 

function onDoctorSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divDoctorsGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while saving.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while saving..");   
		$("#alertError").show();  
	} 

	$("#hidDocIDSave").val("");  
	$("#formDoctor")[0].reset(); 
} 
 
// UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hidDocIDSave").val($(this).closest("tr").find('#hidDocIDUpdate').val());     
	$("#Dname").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#Nic").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#DoctorPrice").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#Phone").val($(this).closest("tr").find('td:eq(3)').text()); 
}); 

//REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "DoctorsAPI",   
		type : "DELETE",   
		data : "DocID=" + $(this).data("docid"),   
		dataType : "text",   
		complete : function(response, status)   
		{    
			onDoctorDeleteComplete(response.responseText, status);   
		}  
	}); 
}); 

function onDoctorDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 
		
			$("#divDoctorsGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		}

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while deleting.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show();  
	}
}
 
// CLIENT-MODEL========================================================================= 
function validateDoctorForm() 
{  
	// CODE  
	if ($("#Dname").val().trim() == "")  
	{   
		return "Insert Doctor Name.";  
	} 
 
	// NAME  
	if ($("#Nic").val().trim() == "")  
	{   
		return "Insert NIC.";  
	} 
	//PRICE-------------------------------  
	if ($("#DoctorPrice").val().trim() == "")  
	{   
		return "Insert Doctor Price.";  
	} 

	// is numerical value  
	var tmpPrice = $("#DoctorPrice").val().trim();  
	if (!$.isNumeric(tmpPrice))  
	{   
		return "Insert a numerical value for Doctor Price.";  
	} 

	// convert to decimal price  
	$("#DoctorPrice").val(parseFloat(tmpPrice).toFixed(2)); 

	// DESCRIPTION------------------------  
	if ($("#Phone").val().trim() == "")  
	{   
		return "Insert Phone Number.";  
	} 

	return true; 
}