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
<style>
.cliente {
	margin-top:10px;
	border: #cdcdcd medium solid;
	border-radius: 10px;
	-moz-border-radius: 10px;
	-webkit-border-radius: 10px;
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
		
		<a href="#jobSeekerProfile" class="btn btn-warning navbar-btn navbar-right">Me<span class="glyphicon glyphicon-log-in"></span></a>

			<ul class="nav navbar-nav">
				<li><a href="#">Home</a></li>
				<li><a href="#">Jobs</a></li>
				<!-- <li><a href="products.html">Products</a></li> -->
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
    <ul class="nav navbar-nav"><li style="color:grey; padding-left:350px"><a>Welcome, ${sessionScope.username}</a></li>
				</ul>
		</div>

	</div> <!-- End container-->

</nav> <!--End navbar-->
<div class ="row"><div class ="jumbotron" style="background-color:white"></div></div>
<!-- Start of container -->
<div class="container">
  <div class="row text-center">
    <div class="col-sm-4">
    <div class="cliente">
    <article role="profile">
  <aside class="avatar">
  <a href="#profile"><img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/131611/avatar.jpg" alt="Avatar Korra" /></a>
  </aside>
  </article>  
      <div class="row text-center">${username}: <p>Vim</p></div>
      <div class="row text-center">${selfIntroduction}:<p>Yeda</p></div>
    </div>
    </div>
    <div class="col-sm-8">
      <div class="jumbotron"></div> 
     
    </div>
  
  </div>
<!-- End of container -->

<%-- <h1></h1> --%>


<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>
</html>