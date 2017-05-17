<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<html lang="en">
	<h1>Job Details</h1>
		<body>
			<form method="POST" action="/company/<%=session.getAttribute("companyId")%>/positions/${job.getJobId()}/edit">
			<p>Req. No:</p>${job.getJobId()}
			<p>Status: </p><!-- <input type="text" name="status" value=${job.getJobStatus()} />-->
			<select name="status">
			<option selected disabled>Select Status</option>
  				<option value="open">Open</option>
  				<option value="closed">Closed</option>
 				 <option value="cencelled">Cancelled</option>
			</select>
			<p>Title: <input type="text" name="title" value=${job.getJobTitle()} />
			<p>Location: </p><input type="text" name="location" value=${job.getJobLocation()} />
			<p>Description: </p><input type="text" name="description" value=${job.getJobDescription()} />
			<p>Responsibilities: </p><input type="text"name="responsibilities" value=${job.getJobResponsibilities()} />
			
			<p>Salary: </p><input type="text"name="salary" value=${job.getJobSalary()} />
			<input type="submit" value="Save"/>
			</form>
		</body>
	</html>