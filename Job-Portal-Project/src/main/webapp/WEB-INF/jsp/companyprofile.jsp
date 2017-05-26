<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<html lang="en">
	<h1>Company Details</h1>
		<body>
			<p>Name: ${company.getCompanyName()}</p>
			<p>Email:${company.getEmail()} </p>
			<p>Website: <a href=${job.getWebsite()}>${company.getWebsite()}</a></p>
			<p>logoUrl: ${company.getLogoUrl()}</p>
			<p>Address: ${company.getAddress()}</p>
			<p>Description: ${company.getDescription()}</p>
			<form method=GET action="/company/<%=session.getAttribute("companyId")%>/edit">
			<input type="submit" value="Edit"/>
			</form>
		</body>
	</html>