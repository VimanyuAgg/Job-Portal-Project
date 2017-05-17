<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
<h3>${message}</h3>
    <form action="/company/<%=session.getAttribute("companyId")%>/welcome" method="get">
    	<input type="submit" value="Go To Dashboard"/>
    </form>
</body>
</html>