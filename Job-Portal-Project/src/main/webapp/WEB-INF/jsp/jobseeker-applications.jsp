<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<title>Applications</title>
</head>
<body>

<script>
$(document).ready(function(){
	$("select").change(function() {
	  alert( "Handler for .change() called." );
	});
});
</script>
Hi, Below are all your applications
<form action="/updateApplications" method="GET">
<input type="hidden" name="email" value="email"/>
<input type="hidden" id="reject" name="reject" value=""/>
<input type="hidden" id="cancel" name="cancel" value=""/>

<table>

	 <tr>
      <td>Application Id</td>
      <td>Status</td>
      <td>Applied On</td>
      <td>Applied Through</td>
      <td>Take Action</td>
    </tr>
	  <c:forEach items="${applications}" var="application">
  	    <tr>
 	      <td><c:out value="${application.getId()}" /></td>
	      <td><c:out value="${application.getStatus()}" /></td>
	      <td><c:out value="${application.getPostedOn()}" /></td>
	      <td><c:out value="${application.getProfile()}" /></td>
	      <td>
  			  <input type="radio" onclick="addCancel(${application.getId()})" name="${application.getId()}" value="${application.getId()}">Cancel
			  <input type="radio" onclick="addReject(${application.getId()})" name="${application.getId()}" value="${application.getId()}">Reject
	      </td>
	    </tr>
	  </c:forEach>
	</table>
	<input type="submit" value="Submit Changes!"/>
</form>

<script>
function addCancel(str){
	console.log("inside addCancel");
	
	var a = document.getElementById("cancel");
	a.value = a.value + str + ",";
	
	console.log("val "+a.value);
	return false;
}

function addReject(str){
	console.log("inside addCancel");

	var a = document.getElementById("reject");
	a.value = a.value + str + ",";
	
	console.log("val "+a.value);
	return false;
}
</script>

</body>
</html>