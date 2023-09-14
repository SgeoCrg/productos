<%@ page import="com.dao.ProductDAO" %>
<%@ page import="com.dao.SupermarketDAO" %>
<%@ page import="com.entity.Product" %>
<%@ page import="com.entity.Supermarket" %>
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
<title>Editar producto</title>
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
<% ProductDAO dao = new ProductDAO(DBConnect.getConnection()); 
	SupermarketDAO sDao = new SupermarketDAO(DBConnect.getConnection());
	Product editP = new Product();
	if(request.getParameter("id") != null) {
	editP = dao.getProductById(Integer.parseInt(request.getParameter("id")));}
	%>
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
						
						<h5>Editar Producto</h5>
					</div>
							<div>
							<label>Productos</label>
							</div>
					<div>
						<% //ProductDAO dao = new ProductDAO(DBConnect.getConnection()); 
							List<Product> list = dao.getProducts(); %>
						<form action="editProduct" method="post">
							<div class="form-row">
									<!-- <label>Productos</label> -->
									<div class="form-group col-md-6">
									<table style="border-collapse:collapse; border:3px solid purple;width:500px;">
										<tr>
											<th class="text-center" style="width:35%">Nombre</th>
											<th class="text-center" style="width:10%">Precio</th>
											<th class="text-center" style="width:15%">Unidades</th>
											<th class="text-center" style="width:30%">Supermercado</th>
											<th style="width:10%"></th>
										</tr>
									</table>
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
										<% for(Product p : list) { %>
										<tr class="col-md-10">
											<td class="text-center celdaAsignado"><a href="editProduct.jsp?id=<%=p.getId() %>" style="color:black; text-decoration:none;"><%= p.getNombre()%></a></td>
											<td class="text-center"><a href="editProduct.jsp?id=<%=p.getId() %>"style="color:black; text-decoration:none;"><%= p.getPrecio()%></a></td>
											<td class="text-center"><a href="editProduct.jsp?id=<%=p.getId() %>"style="color:black; text-decoration:none;"><%= p.getUnidades()%></a></td>
											<td class="text-center"><a href="editProduct.jsp?id=<%=p.getId() %>"style="color:black; text-decoration:none;"><%= sDao.getSuperMarketById(p.getSupermercado()).getNombre()%></a></td>
											<td><a href="delete?id=<%=p.getId() %>" class="btn delete-img"></a></td>
										</tr>
										<% } %>
									</table>
								</div>
								<!--  <div class="form-group col-md-6">
								</div>-->
								<div class="form-row">
									<div class="form-group">
										<label>Nombre</label>
											<% if (editP.getNombre() == null) {%>
										<input required="required" class="form-control form-control-sm"
											id="exampleInputEmail1" aria-describedby="emailHelp"
											name="name" value = "">
											<% }else{ %>
										<input required="required" class="form-control form-control-sm"
											id="exampleInputEmail1" aria-describedby="emailHelp"
											name="name" value="<%= editP.getNombre() %>">
											<%} %>
									</div>
									<div class="form-group">
										<label style="margin-left:10px; ">Precio</label>
											<% if (editP.getNombre() == null) {%>
										<input required="required" class="form-control form-control-sm"
											id="exampleInputEmail1" aria-describedby="emailHelp"
											name="price" style="margin-left: 10px;" value = "">
											<% }else{ %>
										<input required="required" class="form-control form-control-sm"
											id="exampleInputEmail1" aria-describedby="emailHelp"
											name="price" style="margin-left: 10px;" value="<%= editP.getPrecio() %>">
											<%} %>
									</div>
									<div class="form-group">
										<label style="margin-left: 20px;" >Unidades</label>
											<% if (editP.getNombre() == null) {%>
										<input required="required" class="form-control form-control-sm"
											id="exampleInputEmail1" aria-describedby="emailHelp"
											name="units" style="margin-left: 20px;" value = "">
											<% }else{ %>
										<input required="required" class="form-control form-control-sm"
											id="exampleInputEmail1" aria-describedby="emailHelp"
											name="units" style="margin-left: 20px;" value="<%= editP.getUnidades() %>">
											<%} %>
										<input type="hidden" name="id" value="<%= editP.getId() %>">
									</div>
								</div>
							</div>
							<% if(editP.getNombre() != null) { %>
								<button type="submit" class="btn btn-sm bg-success text-white">Editar</button>
						</form>
						<%} %>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div style="margin-top: 50px;">
		<%@include file="components/footer.jsp" %>
	</div>
</body>
</html>