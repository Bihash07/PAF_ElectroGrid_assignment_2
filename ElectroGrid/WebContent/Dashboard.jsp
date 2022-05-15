<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ElectroGrid (EG)</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="components/jquery-3.2.1.min.js"></script>
<script src="components/Unit.js"></script>

<style>
body, html {
  height: 100%;
  margin: 0;
}

.bgimg {
  background-image: url('image/bg.jpg');
  height: 100%;
  background-position: center;
  background-size: cover;
  position: relative;
  color: #000;
  font-family: "Courier New", Courier, monospace;
  font-size: 20px;
}

.topleft {
font-family: "Courier New", Courier, monospace;
  font-size: 32px;
  position: absolute;
  top: 0;
  left: 16px;
}


.middle {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
}

hr {
  margin: auto;
  width: 40%;
}
.btn {
  border: 2px solid black;
  border-radius:10px;
  background-color: white;
  color: black;
  padding: 12px 24px;
  font-size: 14px;
  font-weight: bold;
  cursor: pointer;
}

/* Green */
.success {
  border-color: #04AA6D;
  color: green;
}

.success:hover {
  background-color: #04AA6D;
  color: white;
}

/* Blue */
.info {
  border-color: #56a0d3;
  color: dodgerblue;
}

.info:hover {
  background: #56a0d3;
  color: white;
}

.logo{
   color:#000;
}
.logo:hover {
  color: white;
}
</style>
</head>
<body>
<div class="bgimg">
  <div class="topleft">
    <p><a href="index.jsp" class="logo">ElectroGrid (EG)</a></p>
  </div>
  <div class="middle">
    <h3>Dash Board</h3><br><br>
    <hr>
    <a href="Unit.jsp"><button class="btn success">Unit</button></a>
    <a href="Bill.jsp"><button class="btn info">Billing</button></a>
</div>
</div>

</body>
</html>
