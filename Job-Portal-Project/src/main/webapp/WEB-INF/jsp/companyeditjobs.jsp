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

<h4>Below are all the jobs your company has posted.</h4>
<form action="/company/<%=session.getAttribute("companyId")%>/editjobs" method="POST">
<input type="hidden" name="jobId" id="jd"/>
	<table>
	<thead>
	 <tr>
      <th style="text-align:center">Job Id</th>
      <th style="text-align:center">Job Title</th>
      <th style="text-align:center">Responsibilities</th>
      <th style="text-align:center">Description</th>
      <th style="text-align:center">Salary</th>
      <th style="text-align:center">Posted On</th>
      <th style="text-align:center">Eligibility</th>
      <th style="text-align:center">Location</th>
      <th style="text-align:center">Status</th>
    </tr>
          </thead>
    <tbody>
	  <c:forEach items="${jobs}" var="job">
  	    <tr>
	      <td align=center><c:out value="${job.getJobId()}" /></td>
	      <td align=center><c:out value="${job.getJobTitle()}" /></td>
	      <td align=center><c:out value="${job.getJobResponsibilities()}" /></td>
	      <td align=center><c:out value="${job.getJobDescription()}" /></td>
	      <td align=center><c:out value="${job.getJobSalary()}" /></td>
	      <td align=center><c:out value="${job.getPostedOn()}" /></td>
	      <td align=center><c:out value="${job.getEligibility()}" /></td>
	      <td align=center><c:out value="${job.getJobLocation()}" /></td>
	      <td align=center><c:out value="${job.getJobStatus()}" /></td>
	      <!-- <td align=center><input type="submit" onclick="setJobId('${job.getJobId()}')" value="Cancel Applicant!"/></td>
  	      <td><a href="/company/<%=session.getAttribute("companyId")%>/positions/${job.getJobId()}/edit">Edit</a></td> -->
	    </tr>
	    <tr>
	    	 		<td></td>
	    	 		<td></td>
	    	 		<td></td>
	    	      <td align=center><input type="submit" onclick="setJobId('${job.getJobId()}')" value="Cancel Applicant!"/></td>
	      	      <td></td>
	      	      <td><a href="/company/<%=session.getAttribute("companyId")%>/positions/${job.getJobId()}/edit">Edit</a></td>
	    			<td></td>
	    			<td></td>
	    			<td></td>
	    </tr>
	  </c:forEach>
	  </tbody>
	</table>
</form>
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