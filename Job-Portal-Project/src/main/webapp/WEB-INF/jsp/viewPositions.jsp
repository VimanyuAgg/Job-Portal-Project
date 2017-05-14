<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
      <td><a href="/company/<%=session.getAttribute("companyId")%>/positons/${position.getJobId()}"><c:out value="${position.getJobId()}" /></a></td>
      <td>Job Title:<c:out value="${position.getJobTitle()}" /></td>
           <td>Job Responsebilities:<c:out value="${position.getJobResponsibilities()}" /></td>
            <td>Job Description<c:out value="${position.getJobDescription()}" /></td>
            	<td>Job Salary<c:out value="${position.getjobSalary()}" /></td>
                <td>Job Posted On<c:out value="${position.getJobPostedOn()}" /></td>
                <td>Job Eligibility<c:out value="${position.getEligibility()}" /></td>
    </tr>
  </c:forEach>
</table>
</body>
</html>