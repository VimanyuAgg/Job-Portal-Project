<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
Welcome: ${email}


<h4>Below are all the jobs your company has posted.</h4>
<form action="/positions/applyjob" method="GET" enctype="multipart/form-data">
<input type="hidden" name="email" value="email"/>
<input type="hidden" name="jobId" id="jd"/>
<input type="hidden" name="profile" value="false" id="pf"/>
<input type="hidden" name="resume" value="true"  id="rs"/>
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
	      <td><input type="submit" value="View Candidates!"/></td>
	    </tr>
	  </c:forEach>
	</table>
</form>




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