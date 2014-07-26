<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Successful Page</title>
<style type="text/css">
     body{
     background-color:#b5a789;
     background-image:url('resources/images/backimg.jpg');
     font-family :Times,erif;
     color:black;
     }
     
   
  </style>
</head>
<body>
<h1><font color="green" align="center" >Congratulations,you have registered successfully!</font></h1>
<form action="login.html" method="GET" id="register-form">
     <button id="register" type="submit">Return to Login Page</button>
</form>		
</body>
</html>