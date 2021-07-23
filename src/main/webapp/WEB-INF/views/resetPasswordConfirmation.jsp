<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
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
<title>Create your password here</title>
<script type="text/javascript">
	function validatePassword() {
		var str1 = document.getElementById("password").value;
		var str2 = document.getElementById("repassword").value;
		if (str1.localeCompare(str2) == 0) {
			alert("Password changed successfully!! Try to login with new passsword!!");
			return true;
		}
		alert("Password Mismatch!!!");
		return false;
	}
</script>
</head>
<body>
	<div class="main">
		<img src="../images/plane.png" alt="logo">
		<h1>The Airport</h1>
		<br>
		<form action="/AirportManagement/app/changePassword" method="post"
			onsubmit="return validatePassword()">
			Enter password: <input class="input" type="password" name="password1"
				id="password"><br> <br> Re-Enter password: <input
				class="input" type="password" name="password2" id="repassword"><br>
			<br> <input class="input" type="submit" name="submit">
		</form>
	</div>
</body>
</html>