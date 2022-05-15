 
    <%	
     String username = request.getParameter("uname");
     String pass = request.getParameter("pass");
    
     if(username.equals("admin") && pass.equals("admin123"))
     {
    	 
      %>
      
      <script> 
        location.href = 'Dashboard.jsp'; 
      </script>
      
      <%
       }
     else if(username.equals("user") && pass.equals("user123.")){
    	 %>
         
         <script> 
           location.href = 'Unit.jsp'; 
         </script>
         
         <%
    	 
     }
     else
       { 
       %>
       
       <script> 
        alert("Admin Login failed!!! Please Check the Username or Password "); 
        location.href = 'login.jsp'; 
       </script>
       
       <%
       }
       %>
       
 