$(document).ready(function()
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
}); 


// SAVE ============================================
$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateAccountForm(); 

if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidAccIDSave").val() == "") ? "POST" : "PUT"; 
$.ajax( 
{ 
url : "AccountsAPI", 
type : type, 
data : $("#formAccount").serialize(), 
dataType : "text", 
complete : function(response, status) 
{ 
onAccountSaveComplete(response.responseText, status); 
} 
}); 
}); 
function onAccountSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divAccountGrid").html(resultSet.data); 
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
$("#hidAccIDSave").val(""); 
$("#formAccount")[0].reset(); 
}
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
	$("#hidAccIDSave").val($(this).closest("tr").find('#hidAccIDUpdate').val());
	$("#cust_name").val($(this).closest("tr").find('td:eq(0)').text());
	$("#address").val($(this).closest("tr").find('td:eq(1)').text());
	$("#nic").val($(this).closest("tr").find('td:eq(2)').text());
	$("#district").val($(this).closest("tr").find('td:eq(3)').text());
	$("#mobile").val($(this).closest("tr").find('td:eq(4)').text());
	$("#e_service").val($(this).closest("tr").find('td:eq(5)').text());
	$("#wire_install").val($(this).closest("tr").find('td:eq(6)').text());
});


$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "AccountsAPI", 
		 type : "DELETE", 
		 data : "acc_no=" + $(this).data("accid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onAccountDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});

function onAccountDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divAccountGrid").html(resultSet.data); 
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

// CLIENT-MODEL================================================================
function validateAccountForm()
{
	// NAME
	if ($("#cust_name").val().trim() == "")
	{
		return "Insert Customer Name.";
	}
	
	// ADDRESS
	if ($("#address").val().trim() == "")
	{
		return "Insert Address.";
	}
	
	// NIC-------------------------------
	if ($("#nic").val().trim() == "")
	{
		return "Insert NIC No.";
	}

	// DISTRICT------------------------
	if ($("#district").val().trim() == "")
	{
		return "Insert District.";
	}
	
	// DISTRICT------------------------
	if ($("#district").val().trim() == "")
	{
		return "Insert District.";
	}
	
	// MOBILE------------------------
	if ($("#mobile").val().trim() == "")
	{
		return "Insert Mobile No.";
	}
	
	// ELECTIC SERVICE------------------------
	if ($("#e_service").val().trim() == "")
	{
		return "Insert ELECTIC SERVICE.";
	}

	// WIRE INSTALLATION------------------------
	if ($("#wire_install").val().trim() == "")
	{
		return "Insert WIRE INSTALLATION.";
	}
	return true;
}






