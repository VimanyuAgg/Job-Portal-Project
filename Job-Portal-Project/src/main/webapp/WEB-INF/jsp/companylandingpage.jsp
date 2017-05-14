<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
Welcome: ${email}

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
</body>
</html>