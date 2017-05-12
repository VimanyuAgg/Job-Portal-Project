<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
<h1>Thanks for registering with us, USER</h1>
<ul>
    <%--@elvariable id="users" type="java.util.List"--%>
        <li>
            <c:out value="${c.getUsername()}"/>
        </li>
</ul>

<!-- <a href="<spring:url value="/user_create.html" />"><spring:message code="user.create"/></a>-->
</body>
</html>