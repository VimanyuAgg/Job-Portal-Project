<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
<h1>Login Form</h1>
<form method="POST" action="/jobseeker/login">
<p>Username</p>
<input type="text" name="username" />
<p>Password</p>
<input type="text" name="password" />
<p>logoImageUrl</p>
<input type="submit"/>

</form>
<h1>Hello from JS</h1>
</body>
</html>