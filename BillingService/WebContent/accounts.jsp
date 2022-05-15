<%@ page import = "com.Account" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
		<title>Customer Account Management</title>
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<script src="Components/jquery-3.2.1.min.js"></script>
		<script src="Components/accounts.js"></script>
	</head>
<body>
	<div class="container">
	<div class="row">
		<div class="col-6">
		
		<h1>Customer Account Management</h1>
		
		<form id="formAccount" name="formAccount">
			Customer Name:
			<input id="cust_name" name="cust_name" type="text"
							class="form-control form-control-sm">
			<br> Address:
			<input id="address" name="address" type="text"
							class="form-control form-control-sm">
			<br> NIC:
			<input id="nic" name="nic" type="text"
							class="form-control form-control-sm">
			<br> District:
			<input id="district" name="district" type="text"
							class="form-control form-control-sm">
			<br> Mobile:
			<input id="mobile" name="mobile" type="text"
							class="form-control form-control-sm">
			<br> Electricity Service:
			<input id="e_service" name="e_service" type="text"
							class="form-control form-control-sm">
			<br> Wiring Installation:
			<input id="wire_install" name="wire_install" type="text"
							class="form-control form-control-sm">
			
			<br>
			<input id="btnSave" name="btnSave" type="button" value="Save"
							class="btn btn-primary">
			<input type="hidden" id="hidAccIDSave"
							name="hidAccIDSave" value="">
		</form>
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div><br>
		
		<br>
		<div id="divAccountGrid">
			<%
				Account accObj = new Account();
				out.print(accObj.readInquiry());
			%>
		</div>
		
</div> </div> </div>
</body>
</html>








