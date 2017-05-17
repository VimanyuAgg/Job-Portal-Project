<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
}

.btn1{
	background: #62C192;
}

.nv{
	background-color: black!Important;
}

.wh{
	color: white!Important;
}

.ack{
	margin-left: 30%;
}

</style>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manage Jobs</title>
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



<div class="container">
	<div class="row" style="padding-top:30px"></div>
	<h4 align="center">Below are all the jobs your company has posted.</h4>
	<br>
	<hr>
	<div max-width="100%">
	<form action="/company/<%=session.getAttribute("companyId")%>/editjobs" method="POST" enctype="multipart/form-data">
	<input type="hidden" name="jobId" id="jd"/>
    <tbody>
	  <c:forEach items="${jobs}" var="job">
	  	<p>Job Id: ${job.getJobId()}</p>
	  	<p>Job Title:${job.getJobTitle()}</p>
	  	<p>Responsibilities: ${job.getJobResponsibilities()}</p>
	  	<p>Description: {job.getJobDescription()}</p>
	  	<p>Salary: ${job.getJobSalary()}</p>
	  	<p>Posted On: ${job.getPostedOn()}</p>
	  	<p>Eligibility: ${job.getEligibility()}</p>
	  	<p>Location: ${job.getJobLocation()}</p>
	  	<p>Status: ${job.getJobStatus()}</p>
	  	<table align="center">
	  		<tr>
	  			<td></td>
	  			<td><p><a href="/company/<%=session.getAttribute("companyId")%>/positions/${job.getJobId()}/edit">Edit this Job Posting</a></p></td>
	  			<td><input class="btn btn-primary btn1" type="submit" onclick="setJobId('${job.getJobId()}')" value="Remove Job"/></td>
	  		</tr>
	  	</table>
	  	<hr>
	  </c:forEach>
	  </tbody>
</form>
	</div>

</div>

<script>
	function setJobId(val){
		var a = document.getElementById("jd");
		jd.value = val;
		
		console.log("jobid is "+document.getElementById("jd").value);
		return false;
	}

</script>

</body>
</html>