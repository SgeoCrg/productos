<%@ page import="com.DB.DBConnect" %>
<%@ page import="java.sql.Connection" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%@include file="components/css.jsp"%>
<style type="text/css">
.back-img{
background: url("img/j1.jpg");
width: 100%;
height: 90vh;
background-repeat: no-repeat;
background-size: cover;
}
</style>

</head>
<body>
<%@include file="components/navbar.jsp" %>

<% Connection conexion=DBConnect.getConnection();%>

<div class="container-fluid back-img">
<div class="text-center">
<h1 class="text-white p-4">
<i class="fa fa-book" aria-hidden="true"></i>  Control de gasto en Supermercado</h1>
</div>
</div>
</body>
<footer>
<%@include file="components/footer.jsp" %>
</footer>
</html>