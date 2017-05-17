<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head> 
<!-- Latest compiled and minified CSS -->

<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link href='http://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<style>

body{
	background-color: #f2f5f6;
}
.head{
	height: 80px;
	background-color: #62C192;
}

</style>
</head>
<body>
<!-- <div class="jumbotron" style="background-color:white">

</div>-->

<div class="container" style="background-color:#efefef">
<div name="head" class="text-center"><h1>Almost there.. ${username}</h1></div>
<form method="POST" action="/jobseeker/authentication">

<div class="form-group row" style="margin-top:25px">
   <label for="codeVerification" class="col-sm-2 col-form-label" >Verify your code</label>
    <div class="col-sm-6">
    <input class="form-control" type="text" placeholder="Enter your verification code" id="codeVerification" name="codeVerification" required="True">
  </div>
</div>
<div class="form-group row">
    <div class="col-sm-2"></div>
    <div class="col-sm-4">    
    <input class="btn btn-info form-control" type="submit" id="submit" name="submit">
	<div class="col-sm-6"></div>
</div>
</div>
<input type="hidden" name="username" value="${username}" />
             
</form>
</div>
</body>
</html>