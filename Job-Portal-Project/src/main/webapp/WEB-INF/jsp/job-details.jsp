<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<html lang="en">
	<head> 
<!--<script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>-->

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




<style>

body{
	background-color: #eaedef;
	background-image: url("1.jpg");
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
	border-bottom-color: #163aaf;
}

.field:focus {
  border-color: #163aaf;
  box-shadow: #163aaf;  
}

.btn1{
	background-color: #163aaf;
	border-color: #62C192;
	margin-left: 60%;
	margin-top: 10px;
	margin-bottom: 10px;
	
}
.btn1: hover{
	background-color: #163aaf;
	border-color: #163aaf;
}

h1{
	margin-left: 30%;
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

.block{
	background-color: #d1dbff;
	padding 20px;
	min-height: 100px;
	margin: 10px;
}
</style>
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
	          <!-- <a class="dropdown-item" href="/company/register">View Job History</a>
	           --><!-- <a class="dropdown-item" href="/company/register">Edit Profile</a> -->
	        </div>
	      </li>
	      
	        <li class="nav-item active">
	        <a class="nav-link mr-sm-2 wh" href="/jobseeker/logout">Log Out <span class="sr-only">(current)</span></a>
	      </li>
	    </ul>
	  </div>
</div>

</nav>
<br>
<div class="container">
		
		<div class="row ">
			<div class="col-md-3"></div>
			<div class="col-md-6 block">
				<h1>Job Details</h1>
				<form action="/positions/applyjob" method="POST" enctype="multipart/form-data">
					<input type="hidden" name="email" value="<%= session.getAttribute("email") %>"/>
					<input type="hidden" name="jobId" id="jd"/>
					<input type="hidden" name="profile" value="false" id="pf"/>
					<input type="hidden" name="resume" value="true"  id="rs"/>
					<table style="margin-left: 18%;">
						<tr>
							<td>Job ID</td>
							<td>${job.getJobId()}</td>
						</tr>
						
						<tr>
							<td>Company Name</td>
							<td>${job.getJobPostedByCompany().getCompanyName()}</td>
						</tr>
						
						<tr>
							<td>Status</td>
							<td>${job.getJobStatus()}</td>
						</tr>
						
						<tr>
							<td>Title</td>
							<td>${job.getJobTitle()}</td>
						</tr>
						
						<tr>
							<td>Location</td>
							<td>${job.getJobLocation()}</td>
						</tr>
						
						<tr>
							<td>Description</td>
							<td>${job.getJobDescription()}</td>
						</tr>
						
						<tr>
							<td>Salary</td>
							<td>${job.getJobSalary()}</td>
						</tr>
						
						<tr>
						<td>Apply with Profile</td>
		      			<td><input type="radio" name="profile" onChange="disableResumeButton(this, value='${job.getJobId()}')"/></td>
		      			
			      		</tr>
			      		<tr>
			      			<td>Apply with Resume</td>
				      		<td><input type="file" name="${job.getJobId()}" id="${job.getJobId()}" onclick="resumeUpload('${job.getJobId()}')"/></td>
				    	</tr>
						
						<tr>
							<td><input type="submit" class = "btn btn-primary btn1" value="Apply Now!"/></td>
						</tr>
					</table>
					
				 	
				</form>
			</div>
			<div class="col-md-3"></div>
</div>
<script>
	function disableResumeButton(obj, val){
		console.log("inside disableResumeButton");
		console.log(val);
		
		var resume = document.getElementById(val);
		resume.disabled=true;
		obj.value = "withresume";
		console.log(obj.value);
		
		document.getElementById("jd").value = val;
		document.getElementById("rs").value = "false";
		document.getElementById("pf").value = "true";
	}
	
	
	function resumeUpload( val){
		console.log("inside resumeUpload");
		console.log(val);
		
		document.getElementById("jd").value = val;
		document.getElementById("rs").value = "true";
		document.getElementById("pf").value = "false"; 
	}
</script>
		</body>
	</html>