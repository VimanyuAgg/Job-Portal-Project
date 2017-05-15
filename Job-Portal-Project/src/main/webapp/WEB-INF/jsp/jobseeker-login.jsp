<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head> 
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link href='http://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
<script
  src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
  <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet"></link>
<script src="${pageContext.request.contextPath}/js/login-validation.js"/></script>
<title>Login</title>
</head>


<body>

	<div class="container">
  <div class="jumbotron">
    <h1 style="text-align:center">Spring Onion</h1>   
    </div>
    </div>
<div class="text-center" name="registrationSuccess">${username}</div>
<div class ="jumbotron" style="text-align:center">

	
   
	<div class="logo">login</div>
	<c:if test="${(\"true\").equals(isNotFound)}">
	<div><p><c:out value = "${notFoundText}"></c:out></p></div>
	</c:if>
	<!-- Main Form -->
	<div class="login-form-1">
	<form method="POST" action="/jobseeker/login" id="login-form" class="text-left" name="jslogin">
			<div class="login-form-main-message"></div>
			<div class="main-login-form">
				<div class="login-group">
					<div class="form-group">
						<label for="username" class="sr-only">Username</label>
						<input type="text" class="form-control" id="username" name="username" placeholder="username">
					</div>
					<div class="form-group">
						<label for="password" class="sr-only">Password</label>
						<input type="password" class="form-control" id="password" name="password" placeholder="password">
					</div>
					<!-- <div class="form-group login-group-checkbox">
						<input type="checkbox" id="lg_remember" name="lg_remember">
						<label for="lg_remember">remember</label>
					</div> -->
				</div>
				<button type="submit" class="login-button"><i class="fa fa-chevron-right"></i></button>
			</div>
			<div class="etc-login-form">
				<p>new user? <a href="/jobseeker/register">create new account</a></p>
			</div>
		</form>
	</div>
	</div>
	<!-- end:Main Form -->

</div>



<!-- <h1>Login Form</h1>
<form method="POST" action="/jobseeker/login">
<p>Username</p>
<input type="text" name="username" />
<p>Password</p>
<input type="text" name="password" />
<p>logoImageUrl</p>
<input type="submit"/>

</form> -->
</center>
</body>
</html>