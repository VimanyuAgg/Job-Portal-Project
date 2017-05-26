<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link href='http://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.js"></script>
<script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">


<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/company-registration.js"></script>
<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet"></link>
<script src="${pageContext.request.contextPath}/js/login-validation.js"/></script>

<title>Login</title>

<style>

body{
margin:0;
padding: 0;
}

.nv{
    background-color: #163aaf!Important;
}

.btn1{
    background-color: #62C192;
    border-color: #62C192;
    margin-left: 70px;
}

.wh{
    color: white!Important;
}

.ack{
        margin-top: 20px;
    margin-left: 35%;
}
.field{
    background: #f2f5f6;
    border-style: none;
    border-bottom-style: solid;
    border-bottom-width: .5px;
    border-bottom-color: #62C192;
}
.field:focus {
  border-color: #62C192;
  box-shadow: #62C192;
}
p{
    margin-left: 100px;
}

</style>

</head>


<body>
<nav class="navbar navbar-toggleable-md navbar-light bg-faded nv">
  <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="container">
      <a class="navbar-brand wh" href="#">Spring Onions</a>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item active dropdown">
            <a class="nav-link dropdown-toggle wh" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Login
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <a class="dropdown-item" href="/jobseeker/login">Login as User</a>
              <a class="dropdown-item" href="/company/login">Login as Company</a>
            </div>
          </li>

          <li class="nav-item active dropdown">
            <a class="nav-link dropdown-toggle wh" data-toggle="dropdown">
              Register
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <a class="dropdown-item" href="/jobseeker/register">Register as User</a>
              <a class="dropdown-item" href="/company/register">Register as Company</a>
            </div>
          </li>

           <li class="nav-item active">
            <a class="nav-link mr-sm-2 wh" href="#">About <span class="sr-only">(current)</span></a>
          </li>
        </ul>
      </div>
</div>

</nav>

<div class="ack" name="registrationSuccess">${username}</div>
<div class ="jumbotron" style="text-align:center">
    <!-- <div class="logo">User Login</div> -->
    <h3 style="margin-left: 90px;">User Login</h3>
    <c:if test="${(\"true\").equals(isNotFound)}">
    <div><p><c:out value = "${notFoundText}"></c:out></p></div>
    </c:if>
    <!-- Main Form -->
    <div class="login-form-1">
    <form method="POST" action="/jobseeker/login" id="login-form" class="text-left" name="jslogin">
            <div class="login-form-main-message"></div>
            <div class="form-group row">
                 <div class="col-md-5"></div>
                <input class="col-md-3 form-control field" type="text" placeholder="Enter your username..." name="username" id="username">
                <div id="output"></div>
            <!-- <div class="main-login-form" style="width:50%; margin:0 auto;">
                <div class="login-group">
                    <div class="form-group">
                        <label for="username" class="sr-only">Username</label>
                        <input type="text" class="form-control" id="username" name="username" placeholder="Enter your username">
                    </div>
                    <div class="form-group">
                        <label for="password" class="sr-only">Password</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="password">
                    </div>
                </div>
                <button type="submit" class="login-button"><i class="fa fa-chevron-right"></i></button>-->
            </div>
            <div class="form-group row">
                <div class="col-md-5"></div>
                <input class="col-md-3 form-control field" type="password" id="password" name="password" placeholder="Enter your password"><div id="output"></div>
            </div>

            <div class="form-group row">
                <div class="col-sm-5"></div>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button id="mySubmit" type="submit" class="col-md-1 btn btn-primary btn1">Submit</button>
            </div>
<!--                <button type="submit" class="login-button"><i class="fa fa-chevron-right"></i></button>
 -->            </div>

            <div class="etc-login-form">
                <p>new user? <a href="/jobseeker/register">create new account</a></p>
            </div>
        </form>
    </div>
    </div>

</body>
</html>