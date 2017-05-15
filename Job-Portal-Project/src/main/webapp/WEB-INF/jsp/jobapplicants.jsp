<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Job Applicants</title>
</head>
<body>

<form>
	<table>
	 <tr>
      <td>Username</td>
      <td> FirstName</td>
      <td> LastName</td>
      <td> Introduction</td>
      <td> Email</td>
      <td> Work Experience</td>
      <td> Education</td>
      <td> Skills</td>
    </tr>
	  <c:forEach items="${applicants}" var="applicant">
  	    <tr>
	      <td><c:out value="${applicant.getUsername()}" /></td>
	      <td><c:out value="${applicant.getFirstName()}" /></td>
	      <td><c:out value="${applicant.getLastName()}" /></td>
	      <td><c:out value="${applicant.getSelfIntroduction()}" /></td>
	      <td><c:out value="${applicant.getEmail()}" /></td>
	      <td><c:out value="${applicant.getWorkExperience()}" /></td>
	      <td><c:out value="${applicant.getEducation()}" /></td>
	      <td><c:out value="${applicant.getSkills()}" /></td>
	    </tr>
	  </c:forEach>
	</table>
</form>

</body>
</html>