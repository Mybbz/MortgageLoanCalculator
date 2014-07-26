<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="css/main.css"/>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
</script>
<script>
	$(document).ready(function() {
      $("#j_username").on("blur",function(){
			$("#userExist").hide();
			$.ajax({
				async:false,
				url:"validation.html",
				type:"POST",
				dataType:"text",
			    data:{name:$("#j_username").val()},
			    success:function(response){
			    	var result = response.toString().trim();
			    	if(result=="true"){
			    		$("#userExist").show();
			    	}
			    },
			    error:function(){
			    	alert("wrong");
			    }
			});
		});
      
      $("#j_cpassword").on("blur",function(){
    	  
    	  $("#wrongPassword").hide();
    	  var pass = document.getElementById("j_password").value;
          var confPass = document.getElementById("j_cpassword").value;
          
          
          if(pass != confPass) {
        	  $("#wrongPassword").show();
          }
    	  
      });
	});
</script>
<style type="text/css">
     body{
     background-color:#b5a789;
     background-image:url('resources/images/backimg.jpg');
     font-family :Times,erif;
     color:black;
     }
     
    
     
     
     #register_form{
      width:660px;
      padding:10px;
      margin:10px;
      box-shadow:
        0pt 2px 5px rgba(50, 208, 79,  0.7),
        0px 0px 8px 5px rgba(108, 123, 126, 0.8) inset;
      border: 1px solid;
      border-radius: 10px;
      }
   
  </style>
</head>
<body>
<h1><font color="green">Welcome to Register Page</font></h1>

<div id="register_form">
<form  action="doRegister.html" method="POST" >
	<table>
		<tr>
			<td>Username: </td>
			<td><input type="text" name="username" id="j_username" required="required"/></td>
			<td>
			   <div class="alert" style="display:none;" id="userExist">
	                <p>User already exists</p>
               </div>
            <td>
		</tr>
		<tr>
		    
			<td>Password: </td>
			<td><input type="password" name="password" id="j_password" required="required"/></td>
		</tr>
		<tr>
		    
			<td>Confirm Password: </td>
			<td><input type="password" name="confirm_password" id="j_cpassword" required="required"/></td>
			<td>
			   <div class="alert" style="display:none;" id="wrongPassword">
	                <p>Password no match</p>
               </div>
            <td>
		</tr>
		<tr>
		    <td>Email Address:</td>
		    <td><input type="email" name="email" required="required"></td>
		<tr>
			<td></td>
			<td>
				<button type="reset">Clear</button>
				<button id="signin" type="submit">Register</button>
			</td>
		</tr>
	</table>
</form>
</div>
<a href="<c:url value='/j_spring_security_logout'/>">Logout</a>
</body>
</html>