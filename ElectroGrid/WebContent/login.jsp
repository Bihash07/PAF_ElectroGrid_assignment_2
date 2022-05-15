<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
<title>ElectroGrid (EG)</title>
<link href="image/d.jpg" rel ="icon" type= "image/icon">
<style>
body { 
  margin: 0;
  background-image: url('image/bg.jpg');
  font-family:Comic Sans MS, Comic Sans, cursive;
  background-size: cover;
}

/*fsf*/

.bgimg {
  background-color:#cbcbcb;
  height: 100%;
  background-position: center;
  background-size: cover;
  border-radius: 30px 15px 30px 15px;
  border:2px solid #000;
  position: relative;
  color: #000;
}
.form{
  margin:20px 30px 20px 30px;
  
}
.form input{
border-radius:5px;
padding: 5px;
border:1px solid #7c7c7c;

}

.btn{
width: 60%;
border-radius:20px;
border:1px solid #000;
margin:0 0 0 35px;

}
.btn:hover {
background-color: #1d2f43;
  color: white;
}

.topleft {
font-family: "Courier New", Courier, monospace;
  font-size: 32px;
  position: absolute;
  top: 0;
  left: 16px;
}
.logo, .text{
text-decoration: none;
   color:#000;
}
.logo:hover,.text:hover {
  color: white;
}
.container{
 width-max:6000px;
  width: 20%;
  background-color: #ededed;
  box-shadow: -10px -8px 4px #a8a8a8;
  margin: 190px 80px 10px 450px;
  padding: 25px 30px;
  border-radius: 30px 15px 30px 15px;
}



</style>
</head>
<body>
	<div class="topleft">
		<p>
			<a href="index.jsp" class="logo">ElectroGrid (EG)</a>
		</p>
	</div>
	<div class="container"> 

		<div class="row">  
		
			<div class="bgimg">

				<form  method="post" class="form" action="login2.jsp"> 
					<h3 class="m-3">Login</h3><br>
				
					 User Name:   <br>
					 <i class="uil uil-user-square"></i>
					<input type="text" placeholder="Enter Your Username" name="uname" required >   <br>
					
					<br> 
					Password:  <br> 
					<i class="uil uil-user-square"></i>
					<input type="password"  placeholder="Enter your password" name="pass" required >   <br>
					
				    				 
					 <br>  
					<div class="checkbox-text">
                        <div class="checkbox-content">
                            <input type="checkbox" id="logCheck">
                            <label for="logCheck" class="text">Remember me</label>
                        </div>
                        
                    </div>

                    <div>
                        <input type="submit"  value="Login" class="btn">
                    </div>
                   <br>
                    <div>
                        <a href="index.jsp"class="text"><span >Back to home</span></a>
                    </div>
                    
				</form>
              </div>
            </div>
        </div>
   
</body>
</html>
