<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	<body>
		<h1>Registration Here!</h1>
		<form method="POST" action="/company/register">
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
			<p>email</p>
			<input type="text" name="email" />
			<p>Password</p>
			<input type="text" name="password" />
			<input type="submit"/>
		</form>
		<p>${errorMessage}</p>
</body>
</html>