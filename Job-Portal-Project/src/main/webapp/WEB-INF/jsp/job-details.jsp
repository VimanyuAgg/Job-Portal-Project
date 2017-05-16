<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<html lang="en">
	<h1>Job Details</h1>
		<body>
		<form action="/positions/applyjob" method="POST" enctype="multipart/form-data">
			<input type="hidden" name="email" value="<%= session.getAttribute("email") %>"/>
			<input type="hidden" name="jobId" id="jd"/>
			<input type="hidden" name="profile" value="false" id="pf"/>
			<input type="hidden" name="resume" value="true"  id="rs"/>
			<p>Req. No: ${job.getJobId()}</p>
			<p>Status: ${job.getJobStatus()}</p>
			<p>Title: ${job.getJobTitle()}</p>
			<p>Location: ${job.getJobLocation()}</p>
			<p>Description: ${job.getJobDescription()}</p>
			<p>Salary: ${job.getJobSalary()}</p>
			<table>
			<tr>
				<td>Apply with Profile</td>
      			<td>Apply with Resume</td>
      		</tr>
      		<tr>
      			<td><input type="radio" name="profile" onChange="disableResumeButton(this, value='${job.getJobId()}')"/></td>
	      		<td><input type="file" name="${job.getJobId()}" id="${job.getJobId()}" onclick="resumeUpload('${job.getJobId()}')"/></td>
	      		<td><input type="submit" value="Apply Now!"/></td>
	    	</tr>
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