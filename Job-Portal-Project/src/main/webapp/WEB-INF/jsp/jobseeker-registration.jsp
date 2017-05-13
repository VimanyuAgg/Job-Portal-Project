<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
<h1>Registration Form for JobSeekers</h1>
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

<form method="POST" action="/jobseeker/register">
<p>First Name</p>
<input type="text" name="firstName" />
<p>Last Name</p>
<input type="text" name="lastName" />
<p>Upload Picture</p>
<input type="text" name="picture" />
<p>Introduction</p>
<input type="text" name="selfIntroduction" />
<p>Highest Education</p>
<input type="text" name="education" />
<p>Work Experience</p>
<input type="text" name="workExperience" />
<p>Comma Separated Skills</p>
<input type="text" name="skills" />
<p>Username</p>
<input type="text" name="username" />
<p>email</p>
<input type="text" name="email" />
<p>Password</p>
<input type="text" name="password" />
<input type="submit"/>
<h1>Register Please !</h1>
</form>

</body>
</html>