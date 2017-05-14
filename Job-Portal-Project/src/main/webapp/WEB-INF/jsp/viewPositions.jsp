<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
Welcome: ${email}
<h1>All positions:</h1>

<h4>Click on a jobID to apply for that job.</h4>
<table>
 <tr>
      <td>Job Id</td>
      <td>Job Title</td>
      <td>Responsibilities</td>
      <td>Description</td>
      <td>Salary</td>
      <td>Posted On</td>
      <td>Eligibility</td>
      <td>Apply with Profile</td>
      <td>Apply with Resume</td>
      
    </tr>

	  <c:forEach items="${positions}" var="position">
    	<form action="/positions/${position.getJobId()}/applyjob" method="POST">
    		<input type="hidden" name="email" value="email"/>
    		<input type="hidden" name="jobId" value="${position.getJobId()}"/>
	  	    <tr>
		      <td><c:out value="${position.getJobId()}" /></td>
		      <td><c:out value="${position.getJobTitle()}" /></td>
		      <td><c:out value="${position.getJobResponsibilities()}" /></td>
		      <td><c:out value="${position.getJobDescription()}" /></td>
		      <td><c:out value="${position.getJobSalary()}" /></td>
		      <td><c:out value="${position.getPostedOn()}" /></td>
		      <td><c:out value="${position.getEligibility()}" /></td>
		      <td><input type="radio" name="profile" onChange="disableResumeButton(this, value='${position.getJobId()}')"/></td>
		      <td><input type="file" name="resume" id="${position.getJobId()}"/></td>
		      <td></td>
		      <td><input type="submit" value="Apply Now!"/></td>
		    </tr>
  		</form>
  	  </c:forEach>
</table>

<script>

function disableResumeButton(obj, val){
	console.log("inside disableResumeButton");
	console.log(val);
	
	var resume = document.getElementById(val);
	resume.disabled=true;
	obj.value = "withresume";
	console.log(obj.value);
}

</script>

</body>
</html>