<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
<h1>Registration Form</h1>
<!-- <form:form method="POST" action="/companyCreate" modelAttribute="form">
    <form:errors path="" element="div"/>
    <div>
        <form:label path="companyName"></form:label>
        <form:input path="companyName"/>
        <form:errors path="companyName"/>
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

<form method="POST" action="/companyRegistration">
<p>Name</p>
<input type="text" name="name" />
<p>Website</p>
<input type="text" name="website" />
<p>logoImageUrl</p>
<input type="text" name="logoImageUrl" />
<p>address</p>
<input type="text" name="address" />
<p>description</p>
<input type="text" name="description" />
<input type="submit"/>

</form>
<h1>HEYYYYYYYY</h1>
</body>
</html>