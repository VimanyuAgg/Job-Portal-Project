<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head> 
<!-- Latest compiled and minified CSS
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/css/jobseeker-registration.css" rel="stylesheet"></link>-->
	
<!-- Optional theme 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jobseeker-registration.css"></link>-->
<!-- Latest compiled and minified JavaScript 
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<script src="${pageContext.request.contextPath}/js/jobseeker-registration.js"></script>-->


<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link href='http://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.js"></script>
<script src="https://npmcdn.com/tether@1.2.4/dist/js/tether.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jobseeker-registration.js"></script>
<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet"></link>
<script src="${pageContext.request.contextPath}/js/login-validation.js"/></script>    

<script>
$(document).ready(function() {
	
	$('#email').blur(function ()
    { 
		if($("#email").val() != "" || $("#email").val() != null){
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
		} //if statement closed here
        
    });

});
</script>
<title>Edit your profile</title>
<!-- <style>
.form-group.required.col-form-label:after { 
    color: #d00;
    content: "*";
    position: absolute;
    margin-left: 8px;
    top:7px;
}
</style> -->
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
		<h1>Edit your profile</h1>
		<br><br>
<div class="container">		

<form method="POST" action="/jobseeker/<%=session.getAttribute("username")%>/update">
<div class="form-group row">
<label for="lastName" class="col-sm-2 col-form-label">Profile picture:</label>
    <div class="col-sm-6">
    
<div class="col-md-2 rounder image-upload">
    <label for="file-input">
        <img class="rounder" id="defaultValue" src="${pageContext.request.contextPath}/img/user-200.png" height="90px" width="90px"/>
    </label>

    <input id="file-input" type="file" onchange="encodeImageFileAsURL(this)" name="picture"/>
</div>
</div>
</div>

<div class="form-group required">
<div class="form-group row" style="margin-top:25px">
   <label for="firstName" class="col-sm-2 col-form-label" >First Name</label>
    <div class="col-sm-6">
    <input class="form-control" type="text" placeholder="Enter your first name" id="firstName" name="firstName" value= "${firstName}" required>
  </div>
</div>
<div class="form-group row">
    <label for="lastName" class="col-sm-2 col-form-label">Last Name:</label>
    <div class="col-sm-6">
    <input class="form-control" type="text" placeholder="Enter your last name" id="lastName" name="lastName" value= "${lastName}" required>
  </div>
</div>

<div class="form-group row">
     <label for="selfIntroduction"  class="col-sm-2 col-form-label">Introduction</label>
    <div class="col-sm-6">
    <textarea class="form-control" rows="4" cols="50" id="selfIntroduction" name="selfIntroduction" placeholder="Tell us about yourself!">${selfIntroduction}</textarea>
  </div>
</div>

<div class="form-group row">
     <label for="education"  class="col-sm-2 col-form-label">Highest Education</label>
    <div class="col-sm-6">
    <input class="form-control" type="text" id="education" placeholder="Enter highest education" name="education" value=  "${education}" required>
  </div>
</div>

<!-- <div class="form-group row">
     <label for="workExperience"  class="col-sm-2 col-form-label">Work Experience</label>
    <div class="col-sm-6">
    <input class="form-control" type="text" id="workExperience" name="workExperience" placeholder="Insert the experience in years" required>
  </div>
</div> -->
<div class="form-group row">
 <label for="workExperience"  class="col-sm-2 col-form-label">Work Experience</label>
<select class="ui dropdown" name="workExperience" >
  <option value="">Work Experience</option>
  <option value="level1"> less 1 Year </option>
  <option value="level2"> 1-3 years</option>
  <option value="level3">More than 3 years</option>
</select>
 </div>
</div> 

<div class="form-group row">
     <label for="skills"  class="col-sm-2 col-form-label">Comma Separated Skills</label>
    <div class="col-sm-6">
    <input class="form-control" type="text" id="skills" name="skills" placeholder="Ex - Python, Java, Ruby etc" value= "${skills}" required>
  </div>
</div>

<div class="form-group row">
     <label for="username"  class="col-sm-2 col-form-label">Username</label>
    <div class="col-sm-6">
    <!-- <input class="form-control" type="text" id="username" name="username" placeholder="Choose a username" value= ${js.getUsername()} required> -->
  	${username}
  </div>
</div>

<div class="form-group row">
     <label for="email"  class="col-sm-2 col-form-label">Email</label>
    <div class="col-sm-6">
    ${email}
   	
 	<div id="output"></div>
  </div>
</div>

<div class="form-group row">
     <label for="password"  class="col-sm-2 col-form-label">Password</label>
    <div class="col-sm-6">
    <input class="form-control" type="password" title="at least eight symbols containing at least one number, one lower, and one upper letter" id="password" name="password" placeholder="Type your password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" required>
  </div>
</div>



<div class="form-group row">
    <div class="col-sm-2"></div>
    <div class="col-sm-4">    
    <input class="form-control btn btn-primary btn1" type="submit" id="submit" name="submit">
	<div class="col-sm-6"></div>
</div>

</div>  
</form>
<p>${errorMessage}</p>
</div>



</body>
</html>