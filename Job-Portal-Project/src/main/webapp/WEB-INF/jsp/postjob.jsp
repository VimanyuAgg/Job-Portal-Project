<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
<h1>Job Post Form</h1>
<form method="POST" action="/company/${companyId}/addjob">
	<p>Job Id</p>
	<input type="text" name="jobId" />
	<p>Title</p>
	<input type="text" name="title" />
	<p>Description</p>
	<input type="text" name="description" />
	<p>Responsibilities</p>
	<input type="text" name="responsibilities" />
	<p>Office location</p>
	<input type="number" name="location" />
	<p>Salary</p>
	<input type="text" name="salary" />
	<p>Eligibility</p>
	<input type="text" name="eligibility" />
	<input type="submit"/>
</form>
</body>
</html>