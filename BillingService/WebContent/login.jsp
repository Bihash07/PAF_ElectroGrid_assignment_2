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
}

/*fsf*/

.bgimg {
  background-color:#cbcbcb;
  height: 100%;
  background-position: center;
  background-size: cover;
  border-radius: 5px 30px 5px 30px;
  border:2px solid #000;
  position: relative;
  color: #000;
}
.form{
  margin:20px 30px 20px 30px;
  
}
.form input{

border:1px solid #7c7c7c;

}

.container{
 width-max:6000px;
  width: 50%;
  background-color: #ededed;
  box-shadow: -10px -8px 4px #a8a8a8;
  margin: 30px 50px 70px 60px;
  padding: 25px 30px;
  border-radius: 20px;
}



.table{
margin:20px 20px 40px 40px;
border:2px sloid;
border-radius:8px;
 display: flex;
  align-items: center;
  justify-content: space-between;
  flex-wrap: wrap;
 
}

</style>
</head>
<body>
  
    <div class="container"> 

		<div class="row">  
		
			<div class="bgimg">
                
                
                <form id="formBill" name="formBill" method="post" class="form" action="Bill.jsp"> 
					<h3 class="m-3">Login</h3><br>
					
					<br> 
					 User Name:   <br>
					 <i class="uil uil-user-square"></i>
					<input type="text" placeholder="Enter Your Username" name="uname" required >   <br>
					
					<br> 
					District ID:  <br> 
					<input type="password" class="password" placeholder="Enter your password" name="pass" required >   <br>
					
				    				 
					 <br>  
					 <input type="submit"  value="Login" class="btn btn-primary">  
					 
				</form>
</div>
                <form class="form" action="login2.jsp" method="POST">
                    
                    <div class="input-field">
                    <i class="uil uil-user-square"></i>
                        <input type="text" placeholder="Enter Your Usename" name="uname" required>
                        
                    </div>
                    <div class="input-field">
                        <input type="password" class="password" placeholder="Enter your password" name="pass" required>
                    </div>

                    <div class="checkbox-text">
                        <div class="checkbox-content">
                            <input type="checkbox" id="logCheck">
                            <label for="logCheck" class="text">Remember me</label>
                        </div>
                        
                    </div>

                    <div class="input-field button">
                        <input type="submit"  value="Login">
                    </div>
                   
                    <div>
                        <a href="index.jsp"><span class="text">Back to home</span></a>
                    </div>
                    
                </form> 
            </div>
        </div>
   
</body>
</html>
