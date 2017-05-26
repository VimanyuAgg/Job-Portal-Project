<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
 <title>Dashboard!</title>
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
backhround: #eaedef;
}

.btn{
    background: #163aaf;
	color: white;   
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



.sidebar{
	background-color: #d1dbff;
	height: 400px;
	width: 100%;
}

.dp{
	margin-top: 30px;
	vertical-align:middle;
	margin-left: 12%;
	/*  border-radius: 50%; */
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
    margin-bottom: 15px;
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

.desc2{
	float: left;
}

a:hover{
	color:white;
}
</style>

</head>
<body onload="initial()">

<nav class="navbar navbar-toggleable-md navbar-light bg-faded nv" style="height: 70px">
  <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="container">
	  <a class="navbar-brand wh" href="#">Spring Onions</a>
	
	  <div class="collapse navbar-collapse" id="navbarSupportedContent">
	    <ul class="navbar-nav ml-auto">
	      
	      <li class="nav-item active">
	        <a class="nav-link mr-sm-2 wh" href="/company/<%=session.getAttribute("companyId")%>/welcome">Home <span class="sr-only">(current)</span></a>
	      </li>
	      
	      <li class="nav-item active dropdown">
	        <a class="nav-link dropdown-toggle wh" data-toggle="dropdown">
	          My Account
	        </a>
	        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
		         <a class="dropdown-item" href="/company/<%=session.getAttribute("companyId")%>/postjob">Post a New Job</a>
		         <a class="dropdown-item" href="/company/<%=session.getAttribute("companyId")%>/welcome">Manage Current Jobs</a>
		         <a class="dropdown-item" href="/company/<%=session.getAttribute("companyId")%>/edit">Edit your Profile</a>
	        </div>
	      <!-- <li class="nav-item active">
	     	<form id="jobs" action="/positions/searchByFields" method="POST">
	        <a class="nav-link mr-sm-2 wh" href="#" onclick="document.getElementById('jobs').submit();">Jobs</a></form>
	      </li> -->
	     
	       <li class="nav-item active">
	        <a class="nav-link mr-sm-2 wh" href="/logout">Log Out <span class="sr-only">(current)</span></a>
	      </li>

	    </ul>
	  </div>
</div>

</nav>

<div class="row outer">
	
		<div class="col-md-1"></div>
		<div class="col-md-2 sidebar" style="text-align">
			<!-- side bar for profile review -->
			<img class="img-responsive dp" src="${logoImageUrl}" width="75%">
		
			<div class="name"><%=session.getAttribute("companyName")%></div>
 			<br>
			<div class="name">${address}</div>
			<div class="name">${website}</div>
		</div>
		
				<div class="col-md-7">
		<form id="form" action="/company/<%=session.getAttribute("companyId")%>/welcome" method="GET">
			<input type="hidden" name="open" id="op" value="${open}">
			<input type="hidden" name="filled" id="fi" value="${filled}">
			<input type="hidden" name="cancelled" id="can" value="${cancelled}">
			<table class="table">
	 		<thead>
				<tr>
		      		<th>Your Job History</th>
		    	</tr>
		    	<tr>
			    	<td>
			    		Filter Jobs:&nbsp;
			    		
					    <input id="open" type="checkbox" value="Open" onclick="addStatus()">Open&nbsp;&nbsp;&nbsp;
					    <input id="filled" type="checkbox" value="Filled" onclick="addStatus()">Filled&nbsp;&nbsp;&nbsp;
					    <input id="cancelled" type="checkbox" value="Cancelled" onclick="addStatus()">Cancelled&nbsp;&nbsp;&nbsp;
					    
			    	</td>
		    	</tr>
		    </thead>
		    </table>
		</form>
		<!-- job card section -->
		<form action="/positions/applicants" method="GET">
			<input type="hidden" name="jobId" id="jd"/>
		
			<table class="table list-of-posts">
		 		
				<c:forEach items="${jobs}" var="job">
		  	    <tbody>
		  	    	<tr class="row item post">
		  	    		<td style="max-width: 100px">
		  	    			<img class="img-responsive cl" src="${job.jobPostedByCompany.getLogoUrl()}">
		  	    			<br>
		  	    			<div class="data" style="color:grey">
		  	    				<div class="desc">
		  	    					Description<br>
		  	    					<c:out value="${job.getJobDescription()}" />
		  	    				</div>
		  	    				
		  	    				<div class="desc">
		  	    					Responsibilities<br>
		  	    					<c:out value="${job.getJobResponsibilities()}" />
		  	    				</div>
		  	    				
		  	    				<div class="desc">
		  	    					Location: <c:out value="${job.getJobLocation()}" />
		  	    				</div>
		  	    				
		  	    				<div class="desc">
		  	    					Posted On: <c:out value="${job.getPostedOn()}" />
		  	    				</div>
		  	    				
		  	    				<div class="desc">
		  	    					Status : <c:out value="${job.getJobStatus()}" />
		  	    				</div>
		  	    				
		  	    				<br>
		  	    				<div class="desc desc2">
									<a id="cli" class="btn bx" href="/company/<%=session.getAttribute("companyId")%>/positions/${job.getJobId()}/edit" onclick="col()">Edit this Job Posting</a>
 									<input type="submit" class="btn" onclick="setJobId('${job.getJobId()}')" value="View Applicants"/>
		  	    				</div>
		  	    			</div>
		  	    		</td>
		  	    		
		  	    		<td style="padding-top: 24px;font-size: 21px;">
		  	    			<c:out value="${job.getJobTitle()}" /><br>
		  	    			<c:out value="${job.getJobId()}" /><br>
		  	    		</td>
				    </tr>	    
			    </tbody>
			    </c:forEach>
			</table>
		 </form>
		</div>	
	

		<div class="col-md-2">
		<!-- right section -->
	
		</div>
	</div>
	<div class="row">
		<div class="col-md-1"></div>
		<div class="col-md-2"></div>
		
		<div class="col-md-7">
			<div class="pagination"></div>
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
	
	function addStatus(){
		console.log("in addStatus()");
		if ($('#open').is(':checked')) {
			var a = document.getElementById("op");
			a.value = "open";
		}
		else{
			var a = document.getElementById("op");
			a.value = "";
		}
		if ($('#filled').is(':checked')) {
			var a = document.getElementById("fi");
			a.value = "filled";
		}
		else{
			var a = document.getElementById("fi");
			a.value = "";
		}
		if ($('#cancelled').is(':checked')) {
			var a = document.getElementById("can");
			a.value = "cancelled";
		}
		else{
			var a = document.getElementById("can");
			a.value = "";
		}
		$('#form').submit();
	}
</script>

<script type="text/javascript">

	function initial(){
		console.log("inside initial");
		var open = document.getElementById("op").value;
		var filled = document.getElementById("fi").value;
		var cancelled = document.getElementById("can").value;
	
		if(open == undefined || open == ""){
				
		}
		else{
			document.getElementById("open").checked = true;
		}
			
		if(filled == undefined || filled == ""){
			
		}
		else{
			document.getElementById("filled").checked = true;
		}
		
		if(cancelled == undefined || cancelled == ""){
			
		}
		else{
			console.log("checking cancelled");
			document.getElementById("cancelled").checked = true;
		}
		
		console.log("open "+document.getElementById("op").value);
		console.log("filled "+document.getElementById("fi").value);
		console.log("cancelled "+document.getElementById("can").value);
	}

	function col(){
		var a = document.getElementById("cli");
		cli.style.color = "white!Important";
	}

</script>

<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/paginate.js"></script>
<script src="${pageContext.request.contextPath}/js/custom.js"></script>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 
 </body>
</html>