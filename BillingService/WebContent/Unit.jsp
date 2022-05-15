<%@page import="model.Unit"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="image/d.jpg" rel ="icon" type= "image/icon">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="components/jquery-3.2.1.min.js"></script>
<script src="components/Unit.js"></script>
</head>
<body>
   <jsp:include page="main/header.jsp"/>
   
	<div class="container">
		
		<div class="row">

			<div class="bgimg">
			
				<br>
				<h3 class="m-3">Unit Service</h3><br>
				
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>

                <form id="formUnit" name="formUnit" method="post" class="form" action="Unit.jsp">
						
					
					District:   <br>
					<input id="dist" name="dist" type="text" >   <br>
					
					<br> 
					District ID:  <br> 
					<input id="distID" name="distID" type="text" >   <br>
					
					<br>
					 Unit Price: <br>
					<input id="unitp" name="unitp" type="text"><br>
					 
				    	
					<br>
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> 
					<input type="hidden" id="hidUnitIDSave" name="hidUnitIDSave" value="">

				</form>
				</div>
				  <br>
 			    <div >
				<div id="divUnitGrid">
					<%
					Unit unitObj = new Unit();
					out.print(unitObj.readUnits());
					%>

				</div>
			</div>
		</div>
	</div>
<jsp:include page="main/footer.jsp"/>
</body>
</html>