<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head> 
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/css/jobseeker-registration.css" rel="stylesheet"></link>
	
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jobseeker-registration.css"></link>
<!-- Latest compiled and minified JavaScript -->
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<script src="${pageContext.request.contextPath}/js/jobseeker-registration.js"></script>
<title>Register</title>
<!-- <style>
.form-group.required.col-form-label:after { 
    color: #d00;
    content: "*";
    position: absolute;
    margin-left: 8px;
    top:7px;
}
</style> -->
</head>
<body>
	<div class="header head"><p style="text-align:left; font-size:30px; font-family:Didot; color:white">Spring Onion</p></div>

<br>
		<h1>Register Here!</h1>
		<br><br>
<div class="container">		

<form method="POST" action="/jobseeker/register">
<div class="form-group row">
<label for="lastName" class="col-sm-2 col-form-label">Profile picture:</label>
    <div class="col-sm-6">
    
<div class="col-md-2 rounder image-upload">
    <label for="file-input">
        <img class="rounder" id="defaultValue" src="${pageContext.request.contextPath}/img/user-200.png" height="90px" width="90px"/>
    </label>

    <input id="file-input" type="file" onchange="encodeImageFileAsURL(this)" name="picture"/>
</div>
</div>
</div>

<div class="form-group required">
<div class="form-group row" style="margin-top:25px">
   <label for="firstName" class="col-sm-2 col-form-label" >First Name</label>
    <div class="col-sm-6">
    <input class="form-control" type="text" placeholder="Enter your first name" id="firstName" name="firstName" required>
  </div>
</div>
<div class="form-group row">
    <label for="lastName" class="col-sm-2 col-form-label">Last Name:</label>
    <div class="col-sm-6">
    <input class="form-control" type="text" placeholder="Enter your last name" id="lastName" name="lastName" required>
  </div>
</div>

<div class="form-group row">
     <label for="selfIntroduction"  class="col-sm-2 col-form-label">Introduction</label>
    <div class="col-sm-6">
    <textarea class="form-control" rows="4" cols="50" id="selfIntroduction" name="selfIntroduction" placeholder="Tell us about yourself!"></textarea>
  </div>
</div>

<div class="form-group row">
     <label for="education"  class="col-sm-2 col-form-label">Highest Education</label>
    <div class="col-sm-6">
    <input class="form-control" type="text" id="education" placeholder="Enter highest education" name="education" required>
  </div>
</div>

<!-- <div class="form-group row">
     <label for="workExperience"  class="col-sm-2 col-form-label">Work Experience</label>
    <div class="col-sm-6">
    <input class="form-control" type="text" id="workExperience" name="workExperience" placeholder="Insert the experience in years" required>
  </div>
</div> -->
<div class="form-group row">
 <label for="workExperience"  class="col-sm-2 col-form-label">Work Experience</label>
<select class="ui dropdown">
  <option name="workExperience" value="">Work Experience</option>
  <option name="workExperience" value="level1"> less 1 Year </option>
  <option name="workExperience" value="level2"> 1-3 years</option>
  <option name="workExperience" value="level3">More than 3 years</option>
</select>
 </div>
</div> 

<div class="form-group row">
     <label for="skills"  class="col-sm-2 col-form-label">Comma Seperated Skills</label>
    <div class="col-sm-6">
    <input class="form-control" type="text" id="skills" name="skills" placeholder="Ex - Python, Java, Ruby etc" required>
  </div>
</div>

<div class="form-group row">
     <label for="username"  class="col-sm-2 col-form-label">Username</label>
    <div class="col-sm-6">
    <input class="form-control" type="text" id="username" name="username" placeholder="Choose a username" required>
  </div>
</div>

<div class="form-group row">
     <label for="email"  class="col-sm-2 col-form-label">Email</label>
    <div class="col-sm-6">
    <input class="form-control" type="email" id="email" name="email" placeholder="enter your email ID" pattern="[^@]+@[^@]+\.[a-zA-Z]{2,6}" required>
  </div>
</div>

<div class="form-group row">
     <label for="password"  class="col-sm-2 col-form-label">Password</label>
    <div class="col-sm-6">
    <input class="form-control" type="password" title="at least eight symbols containing at least one number, one lower, and one upper letter" id="password" name="password" placeholder="Type your password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" required>
  </div>
</div>

<div class="form-group row">
     <label for="confirmPassword"  class="col-sm-2 col-form-label"> Confirm password</label>
    <div class="col-sm-6">
    <input class="form-control" type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm your passowrd" required>
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