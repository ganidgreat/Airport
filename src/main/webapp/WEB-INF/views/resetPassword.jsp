<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
.main {
	width: 100%;
	display: block;
	height: 100%;
	align: center;
	text-align: center;
	align-content: center;
	vertical-align: middle;
}

.input {
	border-radius: 20px;
	background-color: black;
	width: 10%;
	padding-left: 15px;
	color: white;
	border: none;
	border-width: 0px;
	border-style: solid;
	font-size: 20px;
	height: 25px;
	margin-bottom: 5px;
	font-family: Arial, sans-serif;
}

img {
	margin-top: 5%;
	vertical-align: middle;
	width: 60%;
	height: 40%;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reset your password</title>
</head>
<body>
	<div class="main">
		<img src="../images/plane.png" alt="logo">
		<h1>The Airport</h1>
		<br>
		<form action="/AirportManagement/app/resetPassword" method="post">
			<h3>
				Welcome: ${userDetails.getFirstName() } ${userDetails.getLastName() }!!!<br>
			</h3>
			<b>Please verify your details to reset your password<br></b>
			<p>
				Security Question: ${securityQuestion }<br>
			</p>
			<input class="input" type="text" name="givenAnswer" id="answer"
				placeholder="Security Answer" required><br> <br> <input
				class="input" type="submit" name="Submit" value="Reset Password"><br>
			<c:if test="${not empty faliedMessage}">
				<span style="color: #8866ff;">Invalid answer, Please try
					again!!!</span>
			</c:if>
		</form>
	</div>
</body>
</html>