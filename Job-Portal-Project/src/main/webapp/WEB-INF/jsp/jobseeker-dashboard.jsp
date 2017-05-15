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
        
        <a href="jobseeker/login" class="btn btn-warning navbar-btn navbar-right">logout<span class="glyphicon glyphicon-log-in"></span></a>

            <ul class="nav navbar-nav">
                <li><a href="#">Home</a></li>
       
                <li><a href="#">Profile</a></li>
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
            <input type="text" class="form-control" placeholder="Search" name="q">
            <div class="input-group-btn">
                <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
            </div>
        </div>
        </form>
    </div>            
    <ul class="nav navbar-nav"><li style="color:grey; padding-left:250px"><a>Welcome, ${sessionScope.username}</a></li></ul>
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
    <p class="title">${firstName}&nbsp; ${lastName}</p>
    <p class="title">${selfIntroduction}</p>
    
  </div>

    
    
    </div>
    </div>
    <div class="col-sm-8">
      <div class="jobPost">
      <div class="jumbotron">
      	
		
		<c:forEach var="topJobValue" items="${topJobs}">
		<div class="row">
		<c:out value="${topJobValue.getJobTitle()}" />
		</div>
		<div class="row">
		<c:out value="${topJobValue.getJobDescription()}" />
		</div>
		</c:forEach>
	
			
      </div> 
     </div>
  
  </div>
  </div>
  </div>
<!-- End of container -->


<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<%--     <div class="cliente">
    <article role="profile">
  <aside class="avatar">
  <a href="#profile"><img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/131611/avatar.jpg" alt="Avatar Korra" /></a>
  </aside>
  </article>  
      <div class="row text-center">${username}: <p>Vim</p></div>
      <div class="row text-center">${selfIntroduction}:<p>Yeda</p></div>
    </div> --%>


</body>
</html>