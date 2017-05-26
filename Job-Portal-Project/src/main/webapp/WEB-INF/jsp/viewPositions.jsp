<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700' rel='stylesheet' type='text/css'>

	
<title>Job Search</title>
	<script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
	<link href='http://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
	<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/company-registration.js"></script>
	<script src="${pageContext.request.contextPath}/js/login-validation.js"/></script>    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 	<link href="${pageContext.request.contextPath}/css/snackbar.css" rel="stylesheet"></link>
 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
		 
/* <<<<<<< HEAD
function interested(a){		
	 $.ajax({
            type: "post",
            url: "/jobseeker/markInterested",
            data: "jobId=" +String(a),
            success: function(msg, status){  
            	
            	document.getElementById("snackbar").innerHTML =  msg["result"]; 
            	
            	var x = document.getElementById("snackbar");

                x.className = "show";

                // After 3 seconds, remove the show class from DIV
                setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
            	 */

function interested(a){	
		var prevthis =this;
		 $.ajax({
	            
			  	 type: "post",
	            url: "/jobseeker/markInterested",
	            data: "jobId=" +String(a),
	            success: function(msg, status){  
	            	
	            	document.getElementById("snackbar").innerHTML =  msg["result"]; 
	            	
	            	var x = document.getElementById("snackbar");

	                // Add the "show" class to DIV
	                x.className = "show";

	                // After 3 seconds, remove the show class from DIV
	                setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
	                //$("#markButton").attr("disabled", true);
	               
	            	 


	            },
	            error: function(err){
    	        console.log(err);	
            }
	 });
}
</script>

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

.pagination{
	margin-left: 42%;
}


.pagination li
{
    list-style:none;  
    float:left;
    width : 35px;
    height: 30px;
    border: .5px solid #163aaf;
    background-color:white;
    color: #163aaf;
    text-align:center;
    cursor:pointer;
    margin: 2px;
}

.pagination li:hover
{
    background-color: #163aaf;
    border:1px solid #163aaf;
    color: white;
}

.pagination ul
{
    border: 0px;
    padding: 0px;
}

.active-class
{
	background-color: #163aaf !important;
    border:.4 px solid #163aaf !important;
    color: white !important;	
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
			  <a class="dropdown-item" href="/jobseeker/<%=session.getAttribute("username")%>/viewInterestedJobs">View Interested Jobs</a>
	          <a class="dropdown-item" href="/jobseeker/app/<%=session.getAttribute("email")%>">View Applications</a>
	         

	        </div>
	      </li>
	       <li class="nav-item active">
	        <a class="nav-link mr-sm-2 wh" href="/jobseeker/logout">Log Out <span class="sr-only">(current)</span></a>
	      </li>

	    </ul>
	  </div>
</div>

</nav>


<div id="interestMsg"></div>
<div id="snackbar"></div>
<h1>All positions</h1>
<div class="panel-group" id="accordion">
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">Search By Free-text</a>
        </h4>
      </div>
      <div id="collapse1" class="panel-collapse collapse in">
        <div class="panel-body"><div id="form1" class="#collapse">
<form action="/positions" method=POST>
	<input type="text" name="searchString" />
	<input type="submit" value="search"/>
</form>
</div></div>
      </div>
    </div>
    
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse2">Search By Filters</a>
        </h4>
      </div>
      <div id="collapse2" class="panel-collapse collapse">
        <div class="panel-body"><div id="form2" class="#collapse">
<form action="/positions/searchByFields" method="POST">
	<table>
	<tr><td>
	<tr><td>Search By Title</td><td><input type="text" name="title" id="rs"/></td></tr>
	<tr><td>Search By Salary</td><td><input type="text" name="salary" id="rs"/></td></tr>
	<tr><td>Search By Date</td><td><input type="text" name="postedon" id="rs"/></td></tr>
	<tr><td>Search By Location</td><td><input type="text" name="location" id="rs"/></td></tr>
	<tr><td>Search By Status</td><td><input type="text" name="status" id="rs"/></td></tr>
	</table>
	<input type="submit" value="Search Now!"/>
</form>
</div></div>
      </div>
    </div>
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse3">Search by Salary Range</a>
        </h4>
      </div>
      <div id="collapse3" class="panel-collapse collapse">
        <div class="panel-body"><div id="form3" class="#collapse">
<form action="/positions/salarysearch" method=POST>
	<input type="text" name="min" />
	<input type="text" name="max" />
	<input type="submit" value="Search By Salary!"/>
</form>
</div></div>
      </div>
    </div>    
    
     <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse4">Universal Search</a>
        </h4>
      </div>
      <div id="collapse4" class="panel-collapse collapse">
        <div class="panel-body"><div id="form4" class="#collapse">
<form action="/positions/universalsearch" method=POST>
	<table>
	<tr><td>Search By Free-Text</td><td><input type="text" name="searchString" /></td></tr>
	<tr><td>Search By Title</td><td><input type="text" name="title" id="rs"/></td></tr>
	<tr><td>Search By Date</td><td><input type="text" name="postedon" id="rs"/></td></tr>
	<tr><td>Search By Location</td><td><input type="text" name="location" id="rs"/></td></tr>
	<tr><td>Search By Status</td><td><input type="text" name="status" id="rs"/></td></tr>
	<tr><td>Min Range</td><td><input type="text" name="min" /></td></tr>
	<tr><td>Max Range</td><td><input type="text" name="max" /></td></tr>
	</table>
	<input type="submit" value="Universal search"/>
</form>
</div></div>
      </div>
    </div>    
  </div> 

 


<div class="jumbotron" style="background-color:white"></div>
<form action="/positions/applyjob" method="POST" enctype="multipart/form-data">
<input type="hidden" name="email" value="<%= session.getAttribute("email") %>"/>
<input type="hidden" name="jobId" id="jd"/>
<input type="hidden" name="profile" value="false" id="pf"/>
<input type="hidden" name="resume" value="true"  id="rs"/>
	

	<table class="table table-striped">
	 <thead>
      <tr>
      <th>Job Id</th>
      <th>Company</th>
      <th>Job Title</th>
    <th>Responsibilities</th>
      <th>Description</th>
      <th>Location</th>
      <th>Salary</th>
      <th>Posted On</th>
      <th>Eligibility</th>
      <th>Apply with Profile</th>
      <th>Apply with Resume</th>
    </tr>
    </thead>
    <tbody>
	  <c:forEach items="${positions}" var="position">
  	    <tr>
	      <td><a href="/positions/${position.getJobId()}" id="jobid"><c:out value="${position.getJobId()}" /></a></td>
	      <td><c:out value="${position.getJobPostedByCompany().getCompanyName()}" /></td>
   	      <td><c:out value="${position.getJobTitle()}" /></td>
	      <td><c:out value="${position.getJobResponsibilities()}" /></td>
	      <td><c:out value="${position.getJobDescription()}" /></td>
	      <td><c:out value="${position.getJobLocation()}" /></td>
	      <td><c:out value="${position.getJobSalary()}" /></td>
	      <td><c:out value="${position.getPostedOn()}" /></td>
	      <td><c:out value="${position.getEligibility()}" /></td>
	      <td><input type="radio" name="profile" onChange="disableResumeButton(this, value='${position.getJobId()}')"/></td>
	      <td><input type="file" name="${position.getJobId()}" id="${position.getJobId()}" onclick="resumeUpload('${position.getJobId()}')"/></td>
	      <td><input type="submit" value="Apply Now!"/></td>
	      <td><input type="button" value="Mark as interested!" onClick="interested('${position.getJobId()}');" id="markButton"/></td>
	      <%-- <td><input type="hidden" value="${position.getJobId()}" id="hiddenJobID"/></td>
	       --%>
	    </tr>
	  </c:forEach>
	  </tbody>
	</table>
</form>

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