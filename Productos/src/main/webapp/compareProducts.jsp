<%@ page import="com.dao.ProductDAO" %>
<%@ page import="com.dao.SupermarketDAO" %>
<%@ page import="com.entity.Product" %>
<%@ page import="com.DB.DBConnect" %>
<%@ page import="java.util.List" %>
<%@ page import="com.utils.Utils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Compare Products</title>
<%@include file="components/css.jsp" %>
<style type="text/css">
.delete-img{
background: url("img/boton-x.png");
width: 20px;
height: 20px;
background-repeat: no-repeat;
background-size: 20px 20px;
background-position:center;
}
</style>
</head>
<body style="background-color: #f0f1f2;">
<% ProductDAO pDao = new ProductDAO(DBConnect.getConnection()); 
	SupermarketDAO sDao= new SupermarketDAO(DBConnect.getConnection());
	List<Product> list = null;//pDao.getProducts();
	if(request.getParameter("search") != null) {
		list = pDao.getProductsByText(request.getParameter("search"));
	}%>
	<%@include file="components/navbar.jsp" %>
	
	<div class="container p-2">
		<div class="col-md-10 offset-md-1">
			<div class="card">
				<div class="card-body">
					<div class="text-center text-success">
						<i class="fa friend fa-3x"></i>
						
						<c:if test="${not empty succMsg }">
							<div class="alert alert-success" role="alert">${ succMsg }</div>
							<c:remove var="succMsg" />
						</c:if>
						
						<h5>Comparar Productos</h5>
					</div>
							<div>
							<form action="filterProducts" method="post">
							<label>type product</label>
							<input type="text" name="productFilter" id="productFilter" placeholder="fill to search..." required minlength="3">
							<button type="submit" class="btn btn-sm bg-success text-white">Buscar</button>
							</form>
							<label>Productos</label>
							</div>
	<div class="form-group col-md-7" style="overflow:scroll; height:400px; width:700px; margin-top:-12px">
		<table style="border-collapse:collapse; border:3px solid purple; width:100%;">
										<!-- <tr>
											<th class="text-center" style="width:30%">Nombre</th>
											<th class="text-center">Precio</th>
											<th class="text-center">Unidades</th>
											<th class="text-center">Supermercado</th>
											<th></th>
										</tr>-->
			<%if(list != null) {
				for(Product p : list) { %>
					<tr class="col-md-10">
						<td class="text-center celdaAsignado"><a style="color:black; text-decoration:none;"><%= p.getNombre()%></a></td>
						<td class="text-center"><a style="color:black; text-decoration:none;"><%= p.getPrecio()%> €</a></td>
						<td class="text-center"><a style="color:black; text-decoration:none;"><%= sDao.getSuperMarketById(p.getSupermercado()).getNombre()%></a></td>
						
					</tr>
				<% } %>
			<% } %>
		</table>
	</div>

	<div style="margin-top: 50px;">
		<%@include file="components/footer.jsp" %>
	</div>
</body>
</html>