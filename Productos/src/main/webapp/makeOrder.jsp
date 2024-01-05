<%@ page import="com.entity.User" %>
<%@ page import="com.entity.Product" %>
<%@ page import="com.dao.ProductDAO"%>
<%@ page import="com.DB.DBConnect"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hacer pedido</title>
<script type="text/javascript">
function cargarPeriodo() {
	var diasCargados = document.getElementById("periodo").value;
	let diasAMilisegundos = 86400000;
	let fFin = new Date().getFullYear() + "-" + (new Date().getMonth()+1) + "-" + new Date().getDate();
	let fFinString = fFin.split("-");
	let fIni = new Date(fFinString[0], fFinString[1]-1, fFinString[2]).getTime();
	let res;
	if(diasCargados == 1) {
		res = fIni - 13*diasAMilisegundos;
	} else if(diasCargados == 2) {
		res = fIni - 29*diasAMilisegundos;
	} else if(diasCargados == 3) {
		res = fIni - 364*diasAMilisegundos;
	} else {
		diferencialPeriodo = 1;
		fFin = (new Date().getFullYear()-1) + "-" + (new Date().getMonth()+1) + "-" + new Date().getDate();
		fFinString = fFin.split("-");
		fIni = new Date(fFinString[0], fFinString[1]-1, fFinString[2]).getTime();
		res = fIni - 29*diasAMilisegundos;
	}
	res = new Date(res).toISOString().split("T")[0];
	console.log(fFin + " :: " + res);
	window.location.href = "/Productos/makeOrder.jsp?fIni=" + res + "&fFin=" + fFin + "&periodo=" + diasCargados;
}
</script>
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
<body>
	<%@include file="components/navbar.jsp" %>
	
	<% ProductDAO pDAO = new ProductDAO(DBConnect.getConnection()); 
		List<Product> products = new ArrayList<Product>();
		int divisor = 1;
		if(request.getParameter("fIni") != null) {
		products = pDAO.getProductsUsage("" + ((User)session.getAttribute("userobj")).getId(),
				request.getParameter("fIni"), request.getParameter("fFin"));
		if(Integer.parseInt(request.getParameter("periodo")) > 1) {
			switch(Integer.parseInt(request.getParameter("periodo"))) {
			case 2:
			case 4:
				divisor = 2;
				break;
			case 3:
				divisor = 26;
				break;
			}
		}
		}
	%>
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

						<h5>Elaborar pedido</h5>
					</div>
					<div class="form-group col-md-4">
						<label>Periodo de lectura de productos</label>
						<select class="custom-select" id="periodo" name="periodo" onchange="cargarPeriodo()">
							<option disabled selected>choose...</option>
							<option value="1">Últimas 2 semanas</option>
							<option value="2">Último mes</option>
							<option value="3">Último año</option>
							<option value="4">Mismo periodo año anterior</option>
						</select>
					<% System.out.println(products); %>
					</div>
					<% if(products.size() > 0) { %>
					<div>
						<label>Lista de la compra</label>
					</div>
					<div>
						<table id="listadoProductos" style="margin-left: 30px; width: 500px;">
							<tr>
								<th style="text-align:center;">Producto</th>
								<th style="text-align: center;">Cantidad</th>
							</tr>
							<% for (Product p : products) { %>
							<tr>
								<td style="text-align: center"><%= p.getNombre() %></td>
								<td style="text-align:center;"><%= Math.round(p.getNumeroUsado()/divisor) %></td>
							</tr>
							<% } %>
						</table>
					</div>
					<% } %>
				</div>
			</div>
		</div>
	</div>
</body>
</html>