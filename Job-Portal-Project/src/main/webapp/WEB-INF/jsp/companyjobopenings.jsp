<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
Welcome: ${email}
<h1>Following are the open positions:</h1>
<table>
  <c:forEach items="${positions}" var="position">
    <tr>
      <td><c:out value="${position}" /></td>
    </tr>
  </c:forEach>
</table>
</body>
</html>