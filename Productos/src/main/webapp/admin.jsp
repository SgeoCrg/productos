<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>

<%@include file="components/css.jsp" %>
<style type="text/css">
.back-img{
background: url("img/j2.jpg");
height: 90vh;
width: 100%;
background-repeat: no-repeat;
background-size: cover;
}
</style>
</head>
<body>
	<%@include file="components/navbar.jsp" %>
	<div class="container-fluid back-img">
		<div class="text-center">
			<h1 class="text-white p-4">Welcome <%= session.getAttribute("userobj") %></h1>
		</div>
	</div>
<div style="margin-top: 50px;">
<%@include file="components/footer.jsp" %>
</div>
</body>
</html>