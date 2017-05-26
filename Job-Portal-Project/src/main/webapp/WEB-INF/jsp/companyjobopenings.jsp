<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
Welcome: ${email}
<h1>Following are the open positions:</h1>
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
</body>
</html>