<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Register Here!</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
	<link href="${pageContext.request.contextPath}/css/companyregistration.css" rel="stylesheet"></link>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
   
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css"></link>
    
</head>

	<body>
		<nav class="navbar navbar-toggleable-md navbar-light bg-faded nv">
  <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="container">
      <a class="navbar-brand wh" href="#">Spring Onions</a>
    
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item active dropdown">
            <a class="nav-link dropdown-toggle wh" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Login
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <a class="dropdown-item" href="/jobseeker/login">Login as User</a>
              <a class="dropdown-item" href="/company/login">Login as Company</a>
            </div>
          </li>
          
          <li class="nav-item active dropdown">
            <a class="nav-link dropdown-toggle wh" data-toggle="dropdown">
              Register
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <a class="dropdown-item" href="/jobseeker/register">Register as User</a>
              <a class="dropdown-item" href="/company/register">Register as Company</a>
            </div>
          </li>
          
           <li class="nav-item active">
            <a class="nav-link mr-sm-2 wh" href="#">About <span class="sr-only">(current)</span></a>
          </li>
        </ul>
      </div>
</div>

</nav>
		
		<br>
		<p id="error" align="center">There was some error in your request. Please try again.</p>
		<p>${errorMessage}</p>
	</body>
</html>