<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Register Here!</title>
	<script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
	<link href='http://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
	<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.js"></script>

	
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
	
	<!-- <link href="${pageContext.request.contextPath}/css/companyregistration.css" rel="stylesheet"></link>-->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
	<!-- <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/company-registration.js"></script>
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet"></link>
    <script src="${pageContext.request.contextPath}/js/login-validation.js"/></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script>
  UPLOADCARE_PUBLIC_KEY = "fa5724d4e1f724578152";
</script>
<script charset="utf-8" src="//ucarecdn.com/libs/widget/2.10.3/uploadcare.full.min.js"></script>
    <style>
    body{

	background-color: #f2f5f6;
}

.head{
	height: 80px;
	background-color: #62C192;
}

.rounder{
	border-radius: 50%;
}

.field{
	background: #f2f5f6;
	border-style: none;
	border-bottom-style: solid;
	border-bottom-width: .5px;
	border-bottom-color: #62C192;
}

.field:focus {
  border-color: #62C192;
  box-shadow: #62C192;  
}

.btn1{
	background-color: #62C192;
	border-color: #62C192;
	margin-left: 70px;
}

.btn1: hover{
	background-color: #62C192;
	border-color: #62C192;
}

h1{
	margin-left: 520px;
}

#error{
	text-align: middle;
}

body{
margin:0;
padding: 0;
backhround: #f2f5f6;
}

.btn1{
	background: #62C192;
}

.nv{
	background-color: #163aaf!Important;
}

.wh{
	color: white!Important;
}

.ack{
	margin-left: 30%;
}
    </style>
    

     <script type="text/javascript">
        $(document).ready(function() {
            $('#email').keyup(function ()
            {
                $.ajax({
                    type: "get",
                    url: "/test",
                    data: "email=" +$('#email').val(),
                    success: function(msg){      
                    	console.log("I am here outside" +msg);
                            $('#output').html(msg);
                            if(msg.includes("Email Not Available")){
                            	console.log("I am here");
                            	document.getElementById("output").style.color="Red";
                            	document.getElementById("mySubmit").style.backgroundColor="Red";
                            	document.getElementById("mySubmit").style.borderColor="Red";
                            	document.getElementById("mySubmit").disabled = true;
                        }else{
                        	document.getElementById("output").style.color="Green";
                        	document.getElementById("mySubmit").disabled = false;
                        	document.getElementById("mySubmit").style.backgroundColor="#62C192";
                        	document.getElementById("mySubmit").style.borderColor="#62C192";

                        }
                    }
                });
            });

        });
    </script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    
</head>

	<body>
		<!-- <div class="header head"></div>-->
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
		<div class="logo" style="margin-left: 40%">Register Here!</div>
		<br>
		<!-- <h1>Register Here!</h1>
		<br><br> -->

		<form method="POST" action="/company/register">
			<input type="hidden" name = "clogo" id="clogo">
		
			<div class="form-group row">
			    <div class="col-md-4"></div>
			    <input class="col-md-3 form-control field" type="text" placeholder="Enter your company name..." name="name">
			</div>
			
			<div class="form-group row">
			    <div class="col-md-4"></div>
			    <input class="col-md-3 form-control field" type="text" placeholder="Enter your  company website..." name="website">
			</div>
			
			<div class="form-group row">
			    <div class="col-md-4"></div>
			    <input class="col-md-3 form-control field" type="text" placeholder="Enter your HQ address..." name="address">
			</div>
			
			<div class="form-group row">
			    <div class="col-md-4"></div>
			    <input class="col-md-3 form-control field" type="text" placeholder="Enter description..." name="description">
			</div>
			
			<div class="form-group row">
			    <div class="col-md-4"></div>
			    <input class="col-md-3 form-control field" type="text" placeholder="Enter your contact email..." name="email" id="email" pattern="[^@]+@[^@]+\.[a-zA-Z]{2,6}" required><div id="output"></div>
			</div>
			
				<div class="form-group row">
		    <div class="col-md-4"></div>
		    <input class="col-md-3 form-control field" type="password" id="password" title="at least eight symbols containing at least one number, one lower, and one upper letter" name="password" placeholder="Type your password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" required>
		</div>
		<div class="form-group row">
		    <div class="col-md-4"></div>
		    <input class="col-md-3 form-control field" type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm your passowrd" required>
			<div id="divCheckPasswordMatch"></div>
		</div>
			<div class="form-group row">
			    <div class="col-md-4"></div>
			    <label for="file-input">
        			<%-- <img class="rounder" id="defaultValue" name="logoImageUrl"  src="${pageContext.request.contextPath}/img/company-201.jpg" height="90px" width="90px"/> --%>
    			<p>Logo (Optional)</p><input type="hidden" id="file-input2" role="uploadcare-uploader" name="logoImageUrl" onchange="encodeImageFileAsURL(this)" data-images-only="true" />
    			</label>

			    <!-- <input class="col-md-3 form-control field" type="file" onchange="encodeImageFileAsURL(this)" value="upload logo"  name="logoImageUrl"> -->
				 <!-- <input type="file" onchange="encodeImageFileAsURL(this)" value="upload logo"> -->
			</div>
			
			<div class="form-group row">
			    <div class="col-sm-4"></div>
				<button id="mySubmit" type="submit" class="col-md-2 btn btn-primary btn1">Submit</button>		
			</div>
		</form>
	</body>

</html>