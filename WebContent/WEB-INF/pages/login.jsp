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
<script src="http://wcetdesigns.com/assets/javascript/jquery.js"></script>
<script src="http://wcetdesigns.com/assets/javascript/jquery/cookie-plugin.js"></script>
<script>
	$(document).ready(function() {
		if ("<c:out value='${param.login_error}'/>" != "") {
		  	$('#wrongCredentials').show();
		}
		$("#signin").on("click", loginValidation);	
	
	});

	function loginValidation() {
		$("#usernameAndPasswordReq").hide();
		$("#usernameReq").hide();
		$("#passwordReq").hide();   
		$("#wrongCredentials").hide();	
	  	if($("#j_username").val().length == 0 && $("#j_password").val().length == 0) {
	  		$("#usernameAndPasswordReq").show();
	  		return false;
	  	} else if ($("#j_username").val().length == 0) {
	  		$('#usernameReq').show();
	  		return false;
	  	} else if ($("#j_password").val().length == 0) {
	  		$("#passwordReq").show();
	  		return false;
	  	} else {
	  		return true;
	  	}
	}
	function remember_me(){
		   var c = $("#check"); //INPUT CHECKBOX

		   //IF CHECKBOX IS SET, COOKIE WILL BE SET
		   if(c.is(":checked")){
		     var u = $("#j_username").val(); //VALUE OF USERNAME
		     var p = $("#j_password").val(); //VALUE OF PASSWORD
		     $.cookie("username", u, { expires: 3650 }); //SETS IN DAYS (10 YEARS)
		     $.cookie("password", p, { expires: 3650 }); //SETS IN DAYS (10 YEARS)
		   }
		}
	function load_em(){
		   var u = $.cookie("username"); //"USERNAME" COOKIE
		   var p = $.cookie("password"); //"PASSWORD" COOKIE

		   $("#j_username").val(u); //FILLS WITH "USERNAME" COOKIE
		   $("#j_password").val(p); //FILLS WITH "PASSWORD" COOKIE
	}
</script>
<style type="text/css">
     body{
     background-color:#b5a789;
     background-image:url('resources/images/backimg.jpg');
     font-family :Times,erif;
     color:black;
     }
     
    
     
     
     #login{
      width:660px;
      padding:10px;
      margin:10px;
      box-shadow:
        0pt 2px 5px rgba(50, 208, 79,  0.7),
        0px 0px 8px 5px rgba(108, 123, 126, 0.8) inset;
      border: 1px solid;
      border-radius: 10px;
      }
      #logo{
      float:left;
      }
    
     
     
     
     
     



     
     
     
</style>
</head>
<body onLoad="load_em()">

<!-- Alerts for missing form info  --> 
<div class="alert" style="display:none;" id="usernameAndPasswordReq">
	<p>Username and password are required</p>
</div>
<div class="alert" style="display:none;" id="userExist">
	<p>User already exists</p>
</div>
<div class="alert" style="display:none;" id="usernameReq">
	<p>Username is required</p>
</div>
<div class="alert" style="display:none;" id="passwordReq">
	<p>Password is required</p>
</div>
<div class="alert" id="wrongCredentials" style="display:none;">
	<p>The username or password supplied is incorrect</p>
</div>	



<div id="login" >
   <h3><img src='resources/images/home.jpg' width="640px" height="330px"/></h3>
<form name="f" action="<c:url value='j_spring_security_check'/>" method="POST" id="login-form">
	<table>
		<tr>
			<td>Username: </td>
			<td><input type="text" name="j_username" id="j_username" size="32" required="required"/></td>
		</tr>
		<tr>
			<td>Password: </td>
			<td><input type="password" name="j_password" id="j_password" size="32" required="required"/></td>
		</tr>
		<tr>
		  <td></td>
		  <td>
		  <input id="check" type="checkbox">Remember Me
		  <span>&nbsp;&nbsp;</span>
		  <a href="http://www.google.com">Forget password</a>
		  </td>
		  
		  
		  
		</tr>
		<tr>
			<td></td>
			<td>
				<button type="reset">Clear</button>
				<button id="signin" type="submit" onClick="remember_me()" >Sign In</button>
			</td>
		</tr>
	</table>
		
</form>
</div>
<p class="change_link">
     Not a member?
  <a href="register.html" class="to_register">Now join us </a>
<p>


</body>
</html>