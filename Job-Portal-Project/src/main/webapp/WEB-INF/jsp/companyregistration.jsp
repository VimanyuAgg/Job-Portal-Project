<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Register Here!</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
	<link href="${pageContext.request.contextPath}/css/companyregistration.css" rel="stylesheet"></link>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
    <script type="text/javascript" src="companyregistration.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#email').keyup(function ()
            {
                $.ajax({
                    type: "get",
                    url: "/test",
                    data: "email=" +$('#email').val(),
                    success: function(msg){      
                            $('#output').html("   "+msg);
                    }
                });
            });

        });
    </script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

	<body>
		<div class="header head"></div>
		<br>
		<h1>Register Here!</h1>
		<br><br>
		<form method="POST" action="/company/register">
			<div class="form-group row">
			    <div class="col-md-4"></div>
			    <input class="col-md-3 form-control field" type="text" placeholder="Enter your name..." name="name">
			</div>
			
			<div class="form-group row">
			    <div class="col-md-4"></div>
			    <input class="col-md-3 form-control field" type="text" placeholder="Enter your website..." name="website">
			</div>
			
			<div class="form-group row">
			    <div class="col-md-4"></div>
			    <input class="col-md-3 form-control field" type="text" placeholder="Enter your logo image URL..." name="logoImageUrl">
			</div>
			
			<div class="form-group row">
			    <div class="col-md-4"></div>
			    <input class="col-md-3 form-control field" type="text" placeholder="Enter your address..." name="address">
			</div>
			
			<div class="form-group row">
			    <div class="col-md-4"></div>
			    <input class="col-md-3 form-control field" type="text" placeholder="Enter your description..." name="description">
			</div>
			
			<div class="form-group row">
			    <div class="col-md-4"></div>
			    <input class="col-md-3 form-control field" type="text" placeholder="Enter your email..." name="email" id="email"><div id="output"></div>
			</div>
			
			<div class="form-group row">
			    <div class="col-md-4"></div>
			    <input class="col-md-3 form-control field" type="password" placeholder="Enter password..." name="passwors"><div id="output"></div>
			</div>
			
			<div class="form-group row">
			    <div class="col-sm-4"></div>
				<button type="submit" class="col-md-2 btn btn-primary btn1">Submit</button>		
			</div>
		</form>
		
		 
		
	
	
	</body>



	<!-- <body>
		<h1>Registration Here!</h1>
		<form method="POST" action="/company/register">
			<p>Name</p>
			<input type="text" name="name" />
			<p>Website</p>
			<input type="text" name="website" />
			<p>logoImageUrl</p>
			<input type="text" name="logoImageUrl" />
			<p>address</p>
			<input type="text" name="address" />
			<p>description</p>
			<input type="text" name="description" />
			<p>email</p>
			<input type="text" name="email" id="email"/>        <div id="output"></div>
			<p>Password</p>
			<input type="text" name="password" />
			<input type="submit"/>
		</form>
</body> -->
</html>