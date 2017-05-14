<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<html lang="en">
	<h1>Job Details</h1>
		<body>
			<form method="POST" action="/company/<%=session.getAttribute("companyId")%>/positions/${job.getJobId()}/edit">
			<p>Req. No:</p><input type="text" name="jobId" value=${job.getJobId()} />
			<p>Status: </p><input type="text" name="status" value=${job.getJobStatus()} />
			<p>Title: <input type="text" name="title" value=${job.getJobTitle()} />
			<p>Location: </p><input type="text" name="location" />
			<p>Description: </p><input type="text" name="description" value=${job.getJobDescription()} />
			<p>Salary: </p><input type="text"name="salary" value=${job.getJobSalary()} />
			<input type="submit" value="Save"/>
			</form>
		</body>
	</html>