<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Register Here!</title>
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
<style>

	
    
    

body{
	body{
	background-color: #f2f5f6;
	background-image: url("1.jpg");
	color: white;
}
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
html,
body{
margin:0;
padding: 0;
backhround: #f2f5f6;
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
/* .cliente {
    margin-top:10px;
    border: #cdcdcd medium solid;
    border-radius: 10px;
    -moz-border-radius: 10px;
    -webkit-border-radius: 10px;
}
.jobPost {
    margin-top:10px;
    border: #cdcdcd;
    box-shadow: 5px 5px grey;
}
    
.card {
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
    max-width: 300px;
    margin: auto;
    text-align: center;
}

.container1 {
    padding: 0 16px;
}

.title {
    color: grey;
    font-size: 18px;
}

button {
    border: none;
    outline: 0;
    display: inline-block;
    padding: 8px;
    color: white;
    background-color: #000;
    text-align: center;
    cursor: pointer;
    width: 100%;
    font-size: 18px;
}

a {
    text-decoration: none;
    font-size: 22px;
    color: black;
}

button:hover, a:hover {
    opacity: 0.7;
} */

.sidebar{
	background-color: yellow;
	height: 400px;
	width: 100%;
}

.dp{
	margin-top: 30px;
	margin-left: 33%;
	border-radius: 50%;
}

.outer{
	margin-top: 60px;
}

.name{
	text-align: center;
  	margin: 0 auto;
	width: 250px;
	margin-top: 10px;
}

</style>

</head>
<body>

<nav class="navbar navbar-toggleable-md navbar-light bg-faded nv" style="height: 70px">
  <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="container">
	  <a class="navbar-brand wh" href="#">Spring Onions</a>
	
	  <div class="collapse navbar-collapse" id="navbarSupportedContent">
	    <ul class="navbar-nav ml-auto">
	      
	      <li class="nav-item active dropdown">
	        <a class="nav-link dropdown-toggle wh" data-toggle="dropdown">
	          My Account
	        </a>
	        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
	          <a class="dropdown-item" href="/jobseeker/register">View Profile</a>
	          <a class="dropdown-item" href="/company/register">View Job History</a>
	          <a class="dropdown-item" href="/company/register">Edit Profile</a>
	        </div>
	      </li>
	      
	       <li class="nav-item active">
	        <a class="nav-link mr-sm-2 wh" href="#">About <span class="sr-only">(current)</span></a>
	      </li>
	    </ul>
	  </div>
</div>

</nav>

<div class="row outer">
	
	<div class="col-md-1"></div>
	<div class="col-md-3 sidebar" style="text-align">
	<!-- side bar for profile review -->
		<img class="img-responsive dp" src="${pageContext.request.contextPath}/img/user-200.png" width="34%">
	
		<div class="name">Sidharth Bhasin</div>
	</div>
	<div class="col-md-6">
	<!-- job card section -->
	
	
	</div>
	<div class="col-md-2">
	<!-- right section -->
		
	</div>
</div>

    
<nav class="navbar navbar-inverse navbar-fixed-top" id="my-navbar">
    <div class="container">
        <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <!-- <a href="#jobSeekerProfile" class="btn btn-warning navbar-btn navbar-right"><i class="icomoon icon-stack"></i></a>
         --></div>
        <!--Navbar-header-->
        <div class="collapse navbar-collapse" id="navbar-collapse">
        
        <a href="/jobseeker/logout" class="btn btn-warning navbar-btn navbar-right">logout<span class="glyphicon glyphicon-log-in"></span></a>

            <ul class="nav navbar-nav">
                <li><a href="#">Home</a></li>
       
                <li><a href="/jobseeker/profile">Profile</a></li>
                <li style="color:#9d9d9d">
       		
       		    <form class="navbar-form"  action="/positions" method="GET" id="jobForm">
       		    <div class="input-group">
       
                <a href="#" onclick="document.getElementById('jobForm').submit();">Job</a>
            
                 </div>   
                </form> </li>
       		</ul>
                
                <div class="col-sm-3 col-md-3">
        <form class="navbar-form" role="search">
        <div class="input-group">
            <input type="text" class="form-control" placeholder="Search" name="text-search">
            <div class="input-group-btn">
                <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
            </div>
        </div>
        </form>
    </div>            
    <ul class="nav navbar-nav"><li style="color:grey; padding-left:350px"><a>Welcome, ${sessionScope.username}</a></li></ul>
        </div>
        
        

    </div> <!-- End container-->


</nav> <!--End navbar-->

    <div class="container">
  <div class="jumbotron">
    <h1 style="text-align:center">Spring Onion</h1>   
    </div>
    </div>


<!-- Start of container -->
<div class="container">
  <div class="row text-center">
    <div class="col-sm-4">
    <div class="card">

  <%-- <img src="${pageContext.request.contextPath}/img/user-200.png" alt="John" style="width:100%"> --%>

    
 
    <%-- <div class="row text-center">${username}: <p>Vim</p></div>
      <div class="row text-center">${selfIntroduction}:<p>Yeda</p></div>
 --%>

   <img src="${pageContext.request.contextPath}/img/user-200.png" alt="John" height="50%" width="50%">
  
  <div class="container1">
   <h5> <p class="title">${firstName}&nbsp; ${lastName}</p></h5>
    <p class="title">${selfIntroduction}</p>
    
  </div>
    
    </div>
    </div>
    <div class="col-sm-8">
      <div class="jobPost">
      <div class="jumbotron">
      	
		

	
<table class="table">
	 <thead><tr>
      <th>Job Id</th>
      <th>Job Title</th>
      <th>Responsibilities</th>
      <th>Description</th>
      <th>Salary</th>
      <th>Posted On</th>
      <th>Eligibility</th>
     <!--  <td>Apply with Profile</td>
      <td>Apply with Resume</td> -->
    </tr>
	  <c:forEach items="${topJobs}" var="topJobValue">
  	    <tbody>
  	    <tr>
	      <td><c:out value="${topJobValue.getJobId()}" /></td>
	      <td><c:out value="${topJobValue.getJobTitle()}" /></td>
	      <td><c:out value="${topJobValue.getJobResponsibilities()}" /></td>
	      <td><c:out value="${topJobValue.getJobDescription()}" /></td>
	      <td><c:out value="${topJobValue.getJobSalary()}" /></td>
	      <td><c:out value="${topJobValue.getPostedOn()}" /></td>
	      <td><c:out value="${topJobValue.getEligibility()}" /></td>
	      <%-- <td><input type="radio" name="profile" onChange="disableResumeButton(this, value='${position.getJobId()}')"/></td>
	      <td><input type="file" name="${position.getJobId()}" id="${position.getJobId()}" onclick="resumeUpload('${position.getJobId()}')"/></td>
	       --%><td>
	       <form action="/positions/searchByFields" method="POST" >

	       <input type="submit" value="Apply Now!"/></form></td>
	    </tr>
	    </tbody>
	  </c:forEach>
	</table>

      </div> 
     </div>
  
  </div>
  </div>
  </div>


<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>