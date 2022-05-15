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
	var status = validateFbForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	// If valid------------------------  
	var t = ($("#hidfIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "FbAPI",
		type : t,
		data : $("#formFb").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onFbSaveComplete(response.responseText, status);
		}
	});
}); 

function onFbSaveComplete(response, status){
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();
					
			$("#divFbGrid").html(resultSet.data);
	
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
	$("#hidfIDSave").val("");
	$("#formFb")[0].reset();
}

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function() 
		{     
	$("#hidfIDSave").val($(this).closest("tr").find('#hidfIDUpdate').val());
	$("#fName").val($(this).closest("tr").find('td:eq(0)').text());    
	$("#eMail").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#feedBack").val($(this).closest("tr").find('td:eq(2)').text());     

});

//Remove Operation
$(document).on("click", ".btnRemove", function(){
	$.ajax(
	{
		url : "FbAPI",
		type : "DELETE",
		data : "fID=" + $(this).data("fid"),
		dataType : "text",
		complete : function(response, status)
		{
			onFbDeletedComplete(response.responseText, status);
		}
	});
});

function onFbDeletedComplete(response, status)
{
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();
					
			$("#divFbGrid").html(resultSet.data);
	
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
function validateFbForm() {  
	// District 
	if ($("#fName").val().trim() == "")  {   
		return "Insert Username";  
		
	} 
	
	 // DistrictID  
	if ($("#eMail").val().trim() == "")  {   
		return "Insert Email address";  
		
	} 

	 // Unit Price
	if ($("#feedBack").val().trim() == "")  {   
		return "Insert  Feedback";  
		
	} 
		 
	 return true; 
	 
}
