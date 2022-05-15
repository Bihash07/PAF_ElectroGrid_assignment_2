$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	} 
	$("#alertError").hide(); 
}); 

//SAVE ============================================ 
$(document).on("click", "#btnSave", function() 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 

	// Form validation-------------------  
	var status = validateBillForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	// If valid------------------------  
	var t = ($("#hidBillIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "BillAPI",
		type : t,
		data : $("#formBill").serialize(),
		dataType : "text,date",
		complete : function(response, status)
		{
			onBillSaveComplete(response.responseText, status);
		}
	});
}); 

function onBillSaveComplete(response, status){
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();
					
			$("#divBillGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Saving.");
		$("#slertError").show();
	}else{
		$("#alertError").text("Unknown Error while Saving.");
		$("#alertError").show();
	}
	$("#hidBillIDSave").val("");
	$("#formBill")[0].reset();
}

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function() 
		{     
	$("#hidBillIDSave").val($(this).closest("tr").find('#hidBillIDUpdate').val());
	$("#uname").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#distID").val($(this).closest("tr").find('td:eq(1)').text());    
	$("#date").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#reading").val($(this).closest("tr").find('td:eq(3)').text());     

});


//Remove Operation
$(document).on("click", ".btnRemove", function(){
	$.ajax(
	{
		url : "BillAPI",
		type : "DELETE",
		data : "billID=" + $(this).data("billid"),
		dataType : "text",
		complete : function(response, status)
		{
			onBillDeletedComplete(response.responseText, status);
		}
	});
});

function onBillDeletedComplete(response, status)
{
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();
					
			$("#divBillGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Deleting.");
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown Error While Deleting.");
		$("#alertError").show();
	}
}

//CLIENTMODEL
function validateBillForm() {  
	// User Name 
	if ($("#uname").val().trim() == "")  {   
		return "Insert User Name";  
		
	} 
	
	 // DistrictID  
	if ($("#distID").val().trim() == "")  {   
		return "Insert District ID";  
		
	} 

	 // Unit Price
	if ($("#date").val().trim() == "")  {   
		return "Insert Date";  
		
	} 
	// DistrictID  
	if ($("#reading").val().trim() == "")  {   
		return "Insert Unit Reading";  
		
	} 

		 
	 return true; 
	 
}
