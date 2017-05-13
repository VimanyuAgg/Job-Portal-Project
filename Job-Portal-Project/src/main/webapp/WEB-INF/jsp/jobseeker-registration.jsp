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

<!-- Latest compiled and minified JavaScript -->
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<script src="${pageContext.request.contextPath}/js/jobseeker-registration.js"></script>
<title>Register</title>
<style>
.form-group.required.col-form-label:after { 
    color: #d00;
    content: "*";
    position: absolute;
    margin-left: 8px;
    top:7px;
}
</style>
</head>
<body>


<!-- <form:form method="POST" action="/companyCreate" modelAttribute="form">
    <form:errors path="" element="div"/>
    <div>
        <form:label path="First Name"></form:label>
        <form:input path="First Name"/>
        <form:errors path="First Name"/>
    </div>
    <div>
        <form:label path="Last Name"></form:label>
        <form:input path="Last Name"/>
        <form:errors path="Last Name"/>
    </div>
   
   <div>
        <form:label path="Upload Picture"></form:label>
        <form:input path="Upload Picture"/>
        <form:errors path="Upload Picture"/>
    </div>
    <div>
        <form:label path="Introduction"></form:label>
        <form:input path="Introduction"/>
        <form:errors path="Introduction"/>
    </div>
    
    <div>
        <form:label path="Work Experience"></form:label>
        <form:input path="Work Experience"/>
        <form:errors path="Work Experience"/>
    </div>
    
    <div>
        <form:label path="Highest Education"></form:label>
        <form:input path="Highest Education"/>
        <form:errors path="Highest Education"/>
    </div>
    
    
    <div>
        <form:label path="website"></form:label>
        <form:password path="website"/>
        <form:errors path="website"/>
    </div>
    <div>
        <form:label path="logoUrl"></form:label>
        <form:password path="logoUrl"/>
        <form:errors path="logoUrl"/>
    </div>
    <div>
        <form:label path="address"></form:label>
        <form:password path="address"/>
        <form:errors path="address"/>
    </div>
    <div>
        <form:label path="description"></form:label>
        <form:password path="description"/>
        <form:errors path="description"/>
    </div>
    <div>
        <input type="submit"/>
    </div>
</form:form> -->

<div class="container" style="background-color:#efefef">
<div name="head" class="text-center"><h1>Registration</h1></div>
<form method="POST" action="/jobseeker/register">
<div class="form-group required">
<div class="form-group row" style="margin-top:25px">
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
    <input id="input-1" type="file" class="file">
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
    <input class="form-control" type="text" id="education" placeholder="Enter highest education" name="education">
  </div>
</div>

<div class="form-group row">
     <label for="workExperience"  class="col-sm-2 col-form-label">Work Experience</label>
    <div class="col-sm-6">
    <input class="form-control" type="text" id="workExperience" name="workExperience" placeholder="Insert the experience in years">
  </div>
</div>

<div class="form-group row">
     <label for="skills"  class="col-sm-2 col-form-label">Comma Seperated Skills</label>
    <div class="col-sm-6">
    <input class="form-control" type="text" id="skills" name="skills" placeholder="Ex - Python, Java, Ruby etc">
  </div>
</div>

<div class="form-group row">
     <label for="username"  class="col-sm-2 col-form-label">Username</label>
    <div class="col-sm-6">
    <input class="form-control" type="text" id="username" name="username" placeholder="Choose a username">
  </div>
</div>

<div class="form-group row">
     <label for="email"  class="col-sm-2 col-form-label">Email</label>
    <div class="col-sm-6">
    <input class="form-control" type="email" id="email" name="email" placeholder="enter your email ID">
  </div>
</div>

<div class="form-group row">
     <label for="password"  class="col-sm-2 col-form-label">Password</label>
    <div class="col-sm-6">
    <input class="form-control" type="password" id="password" name="password" placeholder="Type your password">
  </div>
</div>

<div class="form-group row">
     <label for="confirmPassword"  class="col-sm-2 col-form-label"> Confirm password</label>
    <div class="col-sm-6">
    <input class="form-control" type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm your passowrd">
  </div>
  <div class="col-sm-6" id="divCheckPasswordMatch"></div>
</div>


<div class="form-group row">
    <div class="col-sm-2"></div>
    <div class="col-sm-4">    
    <input class="btn btn-info form-control" type="submit" id="submit" name="submit">
	<div class="col-sm-6"></div>
</div>

</div>  
</div>


</form>
</div>
</body>
</html>