<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head> 
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jobseeker-registration.css"></link>
<!-- Latest compiled and minified JavaScript -->
<script
  src="https://code.jquery.com/jquery-1.12.4.min.js"
  integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
  crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<script src="${pageContext.request.contextPath}/js/jobseeker-registration.js"></script>
<title>Register</title>
</head>
<body>

<div class="container">
<form method="POST" action="/jobseeker/register">
<div class="form-group row">
   <label for="firstName" class="col-sm-2 col-form-label">First Name</label>
    <div class="col-sm-6">
    <input class="form-control" type="text" placeholder="Enter your first name" id="firstName" name="firstName">
  </div>
</div>
<div class="form-group row">
    <label for="lastName" class="col-sm-2 col-form-label">Last Name:</label>
    <div class="col-sm-6">
    <input class="form-control" type="text" placeholder="Enter your last name" id="lastName" name="lastName">
  </div>
</div>
<div class="form-group row">
     <label for="picture"  class="col-sm-2 col-form-label">Upload picture</label>
    <div class="col-sm-6">
    <input class="form-control" type="text" id="upload" name="upload">
  </div>
</div>

<div class="form-group row">
     <label for="selfIntroduction"  class="col-sm-2 col-form-label">Introduction</label>
    <div class="col-sm-6">
    <textarea class="form-control" rows="4" cols="50" id="selfIntroduction" name="selfIntroduction"></textarea>
  </div>
</div>

<div class="form-group row">
     <label for="education"  class="col-sm-2 col-form-label">Highest Education</label>
    <div class="col-sm-6">
    <input class="form-control" type="text" id="education" name="education">
  </div>
</div>

<div class="form-group row">
     <label for="workExperience"  class="col-sm-2 col-form-label">Work Experience</label>
    <div class="col-sm-6">
    <input class="form-control" type="text" id="workExperience" name="workExperience">
  </div>
</div>

<div class="form-group row">
     <label for="skills"  class="col-sm-2 col-form-label">Comma Seperated Skills</label>
    <div class="col-sm-6">
    <input class="form-control" type="text" id="skills" name="skills">
  </div>
</div>

<div class="form-group row">
     <label for="username"  class="col-sm-2 col-form-label">Username</label>
    <div class="col-sm-6">
    <input class="form-control" type="text" id="username" name="username">
  </div>
</div>

<div class="form-group row">
     <label for="email"  class="col-sm-2 col-form-label">Email</label>
    <div class="col-sm-6">
    <input class="form-control" type="email" id="email" name="email">
  </div>
</div>

<div class="form-group row">
     <label for="password"  class="col-sm-2 col-form-label">Password</label>
    <div class="col-sm-6">
    <input class="form-control" type="password" id="password" name="password">
  </div>
</div>

<div class="form-group row">
     <label for="confirmPassword"  class="col-sm-2 col-form-label"> Confirm password</label>
    <div class="col-sm-6">
    <input class="form-control" type="password" id="confirmPassword" name="confirmPassword">
  </div>
  <div class="col-sm-4" id="divCheckPasswordMatch"></div>
</div>


<div class="form-group row">
    <div class="col-sm-2"></div>
    <div class="col-sm-4">    
    <input class="btn btn-info form-control" type="submit" id="submit" name="submit">
	<div class="col-sm-6"></div>
</div>

</div>  


</form>
</div>
</body>
</html>