<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<html lang="en">
	<h1>Job Details</h1>
		<body>
			<p>Req. No: ${job.getJobId()}</p>
			<p>Status: ${job.getJobStatus()}</p>
			<p>Title: ${job.getJobTitle()}</p>
			<p>Location: ${job.getJobLocation()}</p>
			<p>Description: ${job.getJobDescription()}</p>
			<p>Salary: ${job.getJobSalary()}</p>
			<form method=GET action="/company/<%=session.getAttribute("companyId")%>/positions/${job.getJobId()}/edit">
			<input type="submit" value="Edit"/>
			</form>
		</body>
	</html>