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
	var status = validateUnitForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	// If valid------------------------  
	var t = ($("#hidUnitIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "UnitAPI",
		type : t,
		data : $("#formUnit").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onUnitSaveComplete(response.responseText, status);
		}
	});
}); 

function onUnitSaveComplete(response, status){
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();
					
			$("#divUnitGrid").html(resultSet.data);
	
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
	$("#hidUnitIDSave").val("");
	$("#formUnit")[0].reset();
}

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function() 
		{     
	$("#hidUnitIDSave").val($(this).closest("tr").find('#hidUnitIDUpdate').val());
	$("#dist").val($(this).closest("tr").find('td:eq(0)').text());    
	$("#distID").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#unitp").val($(this).closest("tr").find('td:eq(2)').text());     

});

//Remove Operation
$(document).on("click", ".btnRemove", function(){
	$.ajax(
	{
		url : "UnitAPI",
		type : "DELETE",
		data : "unitID=" + $(this).data("unitid"),
		dataType : "text",
		complete : function(response, status)
		{
			onUnitDeletedComplete(response.responseText, status);
		}
	});
});

function onUnitDeletedComplete(response, status)
{
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();
					
			$("#divUnitGrid").html(resultSet.data);
	
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
function validateUnitForm() {  
	// District 
	if ($("#dist").val().trim() == "")  {   
		return "Insert District";  
		
	} 
	
	 // DistrictID  
	if ($("#distID").val().trim() == "")  {   
		return "Insert District ID";  
		
	} 

	 // Unit Price
	if ($("#unitp").val().trim() == "")  {   
		return "Insert unit Price";  
		
	} 
		 
	 return true; 
	 
}
