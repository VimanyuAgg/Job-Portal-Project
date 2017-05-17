<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<html lang="en">
	<h1>Company Details</h1>
		<body>
			<form method=Post action="/company/<%=session.getAttribute("companyId")%>/edit">
			<p>Name: <input name= "name" value="${company.getCompanyName()}"></p>
			<p>Website: <input name= "website" value="${company.getWebsite()}"> </p>
			<p>Logo Url: <input name= "logoUrl" value="${company.getLogoUrl()}"> </p>
			<p>Address: <input name= "address" value="${company.getAddress()}"> </p>
			<p>Description: <input name= "description" value="${company.getDescription()}"> </p>
			<input type="submit" value="Save"/>
			</form>
		</body>
	</html>