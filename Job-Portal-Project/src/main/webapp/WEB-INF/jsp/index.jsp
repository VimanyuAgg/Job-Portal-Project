<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring Onions</title>
<script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet"></link>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/company-registration.js"></script>
<style>
.nv{
    background-color: #163aaf!Important;
}
</style>

<body>
<div class="outer">
<nav class="navbar navbar-toggleable-md navbar-light bg-faded nv" style="height: 70px">
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
<div class="container">
<p class="banner">	
		Welcome to Spring Onions.
</p>
<h1>Build Your Future Here.</h1>	
	

</div>
</div>
</body>
</html>