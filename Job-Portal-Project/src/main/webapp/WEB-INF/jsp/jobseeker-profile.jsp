<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
	<link href='http://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
	<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/company-registration.js"></script>
<%-- 	<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet"></link>
 --%>	<script src="${pageContext.request.contextPath}/js/login-validation.js"/></script>    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%--     <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet"></link>
 --%>    
 
 
 
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
<script>
  UPLOADCARE_PUBLIC_KEY = "fa5724d4e1f724578152";
</script>
<script charset="utf-8" src="//ucarecdn.com/libs/widget/2.10.3/uploadcare.full.min.js"></script>
<title>Edit your profile</title>
 
<style>

	
    
    

body{
	background-color: #eaedef;
	background-image: url("1.jpg");
}

.head{
	height: 80px;
	background-color: #163aaf;
}

.rounder{
	border-radius: 50%;
}

.field{
	background: #163aaf;
	border-style: none;
	border-bottom-style: solid;
	border-bottom-width: .5px;
	border-bottom-color: #163aaf;
}

.field:focus {
  border-color: #163aaf;
  box-shadow: #163aaf;  
}

.btn1{
	background-color: #163aaf;
	border-color: #163aaf;
	margin-left: 70px;
}

.btn1: hover{
	background-color: #163aaf;
	border-color: #163aaf;
}

h1{
	margin-left: 520px;
}

#error{
	text-align: middle;
}
html,
body{
margin:0;
padding: 0;
backhround: #eaedef;
}

.btn{
    background: #163aaf;
	color: white;   
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



.sidebar{
	background-color: #d1dbff;
	height: 400px;
	width: 100%;
}

.dp{
	margin-top: 30px;
	margin-left: 33%;
	border-radius: 50%;
}

.comp{
	font-size: 14px;
}

.outer{
	margin-top: 60px;
}

.name{
	text-align: center;
  	margin: 0 auto;
	width: 200px;
	margin-top: 10px;
}

.data{
	margin-left: 100px;
    min-width: 100%;
    width: 657px;
}

.desc{

}

.item{
	min-height: 100px;
	width: 100%;
	background-color: #d1dbff;
	margin-left: 5px;
	margin-top: 10px;
	min-height: 100px;
	padding-bottom: 10px;
}

.cl{
	width: 69px;
	height: 69px;
	border-radius: 50%;
	margin-left: 8px;
	margin-top: 8px;
}

.headd{
	float: left;
}



</style>



</head>
<body>

<nav class="navbar navbar-toggleable-md navbar-light bg-faded nv" style="height: 70px">
  <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="container">
	  <a class="navbar-brand wh" href="/jobseeker/<%=session.getAttribute("username")%>/dashboard">Spring Onions</a>
	
	  <div class="collapse navbar-collapse" id="navbarSupportedContent">
	    <ul class="navbar-nav ml-auto">
	      
	      <li class="nav-item active dropdown">
	        <a class="nav-link dropdown-toggle wh" data-toggle="dropdown">
	          My Account
	        </a>
	        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
	          <a class="dropdown-item" href="/jobseeker/<%=session.getAttribute("username")%>/profile">View Profile</a>

<%-- 	          <a class="dropdown-item" href="/jobseeker/app/<%=session.getAttribute("email")%>">View Applications</a>
	          <a class="dropdown-item" href="/company/register">Edit Profile</a>
 --%>

	        <!--   <a class="dropdown-item" href="/company/register">View Job History</a> -->
	        <!--   <a class="dropdown-item" href="/company/register">Edit Profile</a> -->

	          <a class="dropdown-item" href="/jobseeker/app/<%=session.getAttribute("email")%>">View Applications</a>
	           <a class="dropdown-item" href="/jobseeker/<%=session.getAttribute("username")%>/viewInterestedJobs">View Interested Jobs</a>
	         
	         

	        </div>
	        	     <li class="nav-item active">
	     <form id="jobs" action="/positions/searchByFields" method="POST">
	        <a class="nav-link mr-sm-2 wh" href="#" onclick="document.getElementById('jobs').submit();">Jobs</a></form>
	      </li>
	      </li>
	       <li class="nav-item active">
	        <a class="nav-link mr-sm-2 wh" href="/jobseeker/logout">Log Out <span class="sr-only">(current)</span></a>
	      </li>

	    </ul>
	  </div>
</div>

</nav>
<br>
		<h1>Edit your profile</h1>
		<br><br>
<div class="container" style="margin-left: 30%">		

<form method="POST" action="/jobseeker/<%=session.getAttribute("username")%>/update">
<div class="form-group row">
<label for="lastName" class="col-sm-2 col-form-label">Profile picture:</label>
    <div class="col-sm-6">

	   <div class="form-group row">
			<div class="col-md-4"></div>
			<input type="hidden" id="file-input2" role="uploadcare-uploader" name="picture" onchange="encodeImageFileAsURL(this)" data-images-only="true" />
		</div>
</div>
</div>

<div class="form-group required">
<div class="form-group row" style="margin-top:25px">
   <label for="firstName" class="col-sm-2 col-form-label" >First Name</label>
    <div class="col-sm-3">
    <input class="form-control" type="text" placeholder="Enter your first name" id="firstName" name="firstName" value= "${firstName}" required>
  </div>
</div>
<div class="form-group row">
    <label for="lastName" class="col-sm-2 col-form-label">Last Name:</label>
    <div class="col-sm-3">
    <input class="form-control" type="text" placeholder="Enter your last name" id="lastName" name="lastName" value= "${lastName}" required>
  </div>
</div>

<div class="form-group row">
     <label for="selfIntroduction"  class="col-sm-2 col-form-label">Introduction</label>
    <div class="col-sm-3">
    <textarea class="form-control" rows="4" cols="50" id="selfIntroduction" name="selfIntroduction" placeholder="Tell us about yourself!">${selfIntroduction}</textarea>
  </div>
</div>

<div class="form-group row">
     <label for="education"  class="col-sm-2 col-form-label">Highest Education</label>
    <div class="col-sm-3">
    <input class="form-control" type="text" id="education" placeholder="Enter highest education" name="education" value=  "${education}" required>
  </div>
</div>

<div class="form-group row">
 <label for="workExperience"  class="col-sm-2 col-form-label">Work Experience</label>
<select  class="ui dropdown" name="workExperience" >
  <option value="">Work Experience</option>
  <option value="level1"> less 1 Year </option>
  <option value="level2"> 1-3 years</option>
  <option value="level3">More than 3 years</option>
</select>
 </div>
</div> 

<div class="form-group row">
     <label for="skills"  class="col-sm-2 col-form-label">Comma Separated Skills</label>
    <div class="col-sm-3">
    <input class="form-control" type="text" id="skills" name="skills" placeholder="Ex - Python, Java, Ruby etc" value= "${skills}" required>
  </div>
</div>

<div class="form-group row">
     <label for="username"  class="col-sm-2 col-form-label">Username</label>
    <div class="col-sm-3">
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
    <div class="col-sm-3">
    <input class="form-control" type="password" title="at least eight symbols containing at least one number, one lower, and one upper letter" id="password" name="password" placeholder="Type your password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" required>
  </div>
</div>



<div class="form-group row">
    <div class="col-sm-1">   </div> 
    <div class="col-sm-2">    
	    <input class="form-control btn btn-primary btn1" type="submit" id="submit" name="submit">
	</div>
	<div class="col-sm-6"></div>
</div>

</div>  
</form>
<p>${errorMessage}</p>
</div>



</body>
</html>