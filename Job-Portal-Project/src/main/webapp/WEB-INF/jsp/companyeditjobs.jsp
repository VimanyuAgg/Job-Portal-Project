<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manage Jobs</title>
</head>
<body>
<h4>Below are all the jobs your company has posted.</h4>
<form action="/company/<%=session.getAttribute("companyId")%>/editjobs" method="POST">
<input type="hidden" name="jobId" id="jd"/>
	<table>
	 <tr>
      <td>Job Id</td>
      <td>Job Title</td>
      <td>Responsibilities</td>
      <td>Description</td>
      <td>Salary</td>
      <td>Posted On</td>
      <td>Eligibility</td>
      <td>Location</td>
      <td>Status</td>
    </tr>
	  <c:forEach items="${jobs}" var="job">
  	    <tr>
	      <td><c:out value="${job.getJobId()}" /></td>
	      <td><c:out value="${job.getJobTitle()}" /></td>
	      <td><c:out value="${job.getJobResponsibilities()}" /></td>
	      <td><c:out value="${job.getJobDescription()}" /></td>
	      <td><c:out value="${job.getJobSalary()}" /></td>
	      <td><c:out value="${job.getPostedOn()}" /></td>
	      <td><c:out value="${job.getEligibility()}" /></td>
	      <td><c:out value="${job.getJobLocation()}" /></td>
	      <td><c:out value="${job.getJobStatus()}" /></td>
	      <td><input type="submit" onclick="setJobId('${job.getJobId()}')" value="Cancel Applicant!"/></td>
  	      <td><input type="submit" onclick="setJobId('${job.getJobId()}')" value="Cancel Applicant!"/></td>
  	      <td><a href="/company/<%=session.getAttribute("companyId")%>/positions/${job.getJobId()}/edit">Edit</a></td>
	   	  
	    </tr>
	  </c:forEach>
	</table>
</form>
<script>
	function setJobId(val){
		var a = document.getElementById("jd");
		jd.value = val;
		
		console.log("jobid is "+document.getElementById("jd").value);
		return false;
	}

</script>

</body>
</html>