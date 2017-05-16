<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Login Here!</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
	<link href="${pageContext.request.contextPath}/css/companyregistration.css" rel="stylesheet"></link>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
    <script type="text/javascript" src="companyregistration.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body>

		<div class="header head"></div>
		<br>
		<h1>Login Here!</h1>
		<br>
		<div class="text-center" name="registrationSuccess">${username}</div>
		<br>
		<form method="POST" action="/company/login">
			<div class="form-group row">
			    <div class="col-md-4"></div>
			    <input class="col-md-3 form-control field" type="text" placeholder="Enter your email..." name="email">
			</div>
			
			<div class="form-group row">
			    <div class="col-md-4"></div>
			    <input class="col-md-3 form-control field" type="text" placeholder="Enter your password..." name="password">
			</div>
			
			<div class="form-group row">
			    <div class="col-sm-4"></div>
				<button type="submit" class="col-md-2 btn btn-primary btn1">Submit</button>		
			</div>
		</form>

	<c:if test="${(\"true\").equals(isNotFound)}">
	<div><p><c:out value = "${notFoundText}"></c:out></p></div>
	</c:if>

<!-- <h1>Login Form</h1>
<form method="POST" action="/company/login">
<p>Email</p>
<input type="text" name="email" />
<p>Password</p>
<input type="text" name="password" />
<p>logoImageUrl</p>
<input type="submit"/>

</form>
 -->
 </body>
</html>