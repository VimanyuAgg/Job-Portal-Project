<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
Welcome: ${email}
<h1>All positions:</h1>

<h4>Click on a jobID to apply for that job.</h4>
<form action="/positions" method="GET">
	Search By Title<input type="text" name="title" id="rs"/>
	Search By Salary<input type="text" name="salary" id="rs"/>
	Search By Date<input type="text" name="postedon" id="rs"/>
	Search By Location<input type="text" name="location" id="rs"/>
	Search By Status<input type="text" name="status" id="rs"/>
	<input type="submit" value="Search Now!"/>
</form>
<form action="/positions" method=POST>
	<input type="text" name="searchString" />
	<input type="submit" value="search"/>
</form>

<form action="/positions/salarysearch" method=POST>
	<input type="text" name="min" />
	<input type="text" name="max" />
	<input type="submit" value="Search By Salary!"/>
</form>

<form action="/positions/applyjob" method="POST" enctype="multipart/form-data">
<input type="hidden" name="email" value="<%= session.getAttribute("email") %>"/>
<input type="hidden" name="jobId" id="jd"/>
<input type="hidden" name="profile" value="false" id="pf"/>
<input type="hidden" name="resume" value="true"  id="rs"/>


	<table>
	 <tr>
      <td>Job Id</td>
     <!--   <td>Company</td> --> 
      <td>Job Title</td>
    <td>Responsibilities</td>
      <td>Description</td>
      <td>Location</td>
      <td>Salary</td>
      <td>Posted On</td>
      <td>Eligibility</td>
      <td>Apply with Profile</td>
      <td>Apply with Resume</td>
    </tr>
	  <c:forEach items="${positions}" var="position">
  	    <tr>
	      <td><a href="/positions/${position.getJobId()}"><c:out value="${position.getJobId()}" /></a></td>
	      <td><c:out value="${position.getJobTitle()}" /></td>
   	      <td><c:out value="${position.getJobTitle()}" /></td>
<!-- 	      <td><c:out value="${position.getJobResponsibilities()}" /></td> -->
	      <td><c:out value="${position.getJobDescription()}" /></td>
	      <td><c:out value="${position.getJobLocation()}" /></td>
	      <td><c:out value="${position.getJobSalary()}" /></td>
	      <td><c:out value="${position.getPostedOn()}" /></td>
	      <td><c:out value="${position.getEligibility()}" /></td>
	      <td><input type="radio" name="profile" onChange="disableResumeButton(this, value='${position.getJobId()}')"/></td>
	      <td><input type="file" name="${position.getJobId()}" id="${position.getJobId()}" onclick="resumeUpload('${position.getJobId()}')"/></td>
	      <td><input type="submit" value="Apply Now!"/></td>
	    </tr>
	  </c:forEach>
	</table>
</form>

<script>

function disableResumeButton(obj, val){
	console.log("inside disableResumeButton");
	console.log(val);
	
	var resume = document.getElementById(val);
	resume.disabled=true;
	obj.value = "withresume";
	console.log(obj.value);
	
	document.getElementById("jd").value = val;
	document.getElementById("rs").value = "false";
	document.getElementById("pf").value = "true";
}


function resumeUpload( val){
	console.log("inside resumeUpload");
	console.log(val);
	
	document.getElementById("jd").value = val;
	document.getElementById("rs").value = "true";
	document.getElementById("pf").value = "false"; 
}

</script>

</body>
</html>