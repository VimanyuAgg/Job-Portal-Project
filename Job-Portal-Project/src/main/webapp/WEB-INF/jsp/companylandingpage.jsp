<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Spring Onions</title>
    <meta name="description" content="Spring Onions">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
.cliente {
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
}

</style>

</head>
<body>
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
    <h1 style="text-align:center">Spring Onions</h1>   
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
   <h5> <p class="title">Welcome <%=session.getAttribute("companyName")%></p></h5>
  </div>
  </div>
  </div>
 <div class="col-sm-8">
      <div class="jobPost" style=
    "width: 880px;">
      <div class="jumbotron"style="padding:0px;">
     

<h4>Below are all the jobs your company has posted.</h4>
<form action="/positions/applicants" method="GET">
<input type="hidden" name="jobId" id="jd"/>
	<table class="table">
<thead><tr>      <td>Job Id</td>
      <td>Job Title</td>
      <td>Responsibilities</td>
      <td>Description</td>
      <td>Salary</td>
      <td>Posted On</td>
      <td>Eligibility</td>
      <td>Location</td>
      <td>Status</td>
    </tr></thead>
	  <c:forEach items="${jobs}" var="job">
	  <tbody>
  	    <tr>
	      <td><c:out value="${job.getJobId()}" /></td>
	      <td><c:out value="${job.getJobTitle()}" /></td>
	      <td><c:out value="${job.getJobResponsibilities()}" /></td>
	      <td><c:out value="${job.getJobDescription()}" /></td>
	      <td><c:out value="${job.getJobSalary()}" /></td>
	      <td><c:out value="${job.getPostedOn()}" /></td>
	      <td><c:out value="${job.getEligibility()}" /></td>
	      <td><c:out value="${job.getJobLocation()}" /></td>
	      <td><c:out value="${job.getJobStatus()}" /></td>
	      <td><input type="submit" onclick="setJobId('${job.getJobId()}')" value="View Applicants!"/></td>
	    </tr>
	    </tbody>
	  </c:forEach>
	</table>
</form>

<%-- <form action="/company/<%=session.getAttribute("companyId")%>/postjob" method="GET">
 --%>
 <form action="/company/<%=session.getAttribute("companyId")%>/postjob" method="GET">
	<input type="submit" value="Post a new Job" style="
    /* float: left; */
    margin-bottom: 15px;"/>
</form>

<form action="/company/<%=session.getAttribute("companyId")%>/managejobs" method="GET">
	<input type="submit" value="Manage Current Jobs" style="
    /* float: left; */
    margin-bottom: 15px;"/>
</form>
</div>
</div></div>
 
  </div>
  </div>
<script>
	function setJobId(val){
		var a  = document.getElementById("jd");
		a.value = "";
		
		console.log("Form Submitted");
		
		a.value = val;
		console.log("job id is "+document.getElementById("jd").value);
		return false;
	}
</script>


<%-- 

<h1>Positions Posted:</h1>
<table>
 <tr>
      <td>Job Id</td>
      <td>Job Title</td>
      <td>Status</td>
      
    </tr>
  <c:forEach items="${positions}" var="position">
    <tr>
      <td><a href="/company/<%=session.getAttribute("companyId")%>/positions/${position.getJobId()}"><c:out value="${position.getJobId()}" /></a></td>
      <td><c:out value="${position.getJobTitle()}" /></td>
            <td><c:out value="${position.getJobStatus()}" /></td>
    </tr>
  </c:forEach>
</table>
 <form method="GET" action="/company/<%=session.getAttribute("companyId")%>/addjob">
	<input type="submit" value="Post a new position"/>
</form>
<form method="GET" action="/company/<%=session.getAttribute("companyId")%>/profile">
	<input type="submit" value="My Account"/>
</form>
--%>
 
 </body>
</html>