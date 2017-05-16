<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
Welcome: ${email}


<h4>Below are all the jobs your company has posted.</h4>
<form action="/positions/applicants" method="GET">
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
	      <td><input type="submit" onclick="setJobId('${job.getJobId()}')" value="View Applicants!"/></td>
	    </tr>
	  </c:forEach>
	</table>
</form>

<%-- <form action="/company/<%=session.getAttribute("companyId")%>/postjob" method="GET">
 --%>
 <form action="/company/1/postjob" method="GET">
	<input type="submit" value="Post a new Job"/>
</form>

<form action="/company/1/managejobs" method="GET">
	<input type="submit" value="Manage Current Jobs"/>
</form>

<script>
	function setJobId(val){
		var a  = document.getElementById("jd");
		a.value = "";
		
		console.log("Form Submitted");
		
		a.value = val;
		console.log("job id is "+document.getElementById("jd").value);
		return false;
	}
</script>


<%-- 

<h1>Positions Posted:</h1>
<table>
 <tr>
      <td>Job Id</td>
      <td>Job Title</td>
      <td>Status</td>
      
    </tr>
  <c:forEach items="${positions}" var="position">
    <tr>
      <td><a href="/company/<%=session.getAttribute("companyId")%>/positions/${position.getJobId()}"><c:out value="${position.getJobId()}" /></a></td>
      <td><c:out value="${position.getJobTitle()}" /></td>
            <td><c:out value="${position.getJobStatus()}" /></td>
    </tr>
  </c:forEach>
</table>
 <form method="GET" action="/company/<%=session.getAttribute("companyId")%>/addjob">
	<input type="submit" value="Post a new position"/>
</form>
<form method="GET" action="/company/<%=session.getAttribute("companyId")%>/profile">
	<input type="submit" value="My Account"/>
</form>
--%>
 
 </body>
</html>