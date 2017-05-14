<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#email').keyup(function ()
            {
                $.ajax({
                    type: "get",
                    url: "/test", //this is my servlet
                    data: "email=" +$('#email').val(),
                    success: function(msg){      
                            $('#output').html(msg);
                    }
                });
            });

        });
    </script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
	<body>
		<h1>Registration Here!</h1>
		<form method="POST" action="/company/register">
			<p>Name</p>
			<input type="text" name="name" />
			<p>Website</p>
			<input type="text" name="website" />
			<p>logoImageUrl</p>
			<input type="text" name="logoImageUrl" />
			<p>address</p>
			<input type="text" name="address" />
			<p>description</p>
			<input type="text" name="description" />
			<p>email</p>
			<input type="text" name="email" id="email"/>        <div id="output"></div>
			<p>Password</p>
			<input type="text" name="password" />
			<input type="submit"/>
		</form>
</body>
</html>