<%@ page import="com.entity.User" %>
<%@ page import="com.entity.Product" %>
<%@ page import="com.dao.ProductDAO"%>
<%@ page import="com.DB.DBConnect"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.time.LocalDate" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products Usage</title>
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
		if(request.getParameter("id") != null) {
		products = pDAO.getProductsUsage(request.getParameter("id"),
				request.getParameter("fIni"), request.getParameter("fFin"));
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

						<h5>Ver productos comprados</h5>
					</div>	
					<div class="form-row">
						<div class="form-group col-md-5">
							<form action="getUsage" method="post">
								<div class="form-row">
									<div class="form-group col-md-4">
										<input type="hidden" name="userId" id="userId" 
											value="<%= ((User)session.getAttribute("userobj")).getId() %>">
										<label>Fecha inicial:</label>
										<input type="date" name="dateIni" id="dateIni" min="2023-01-01" 
										<% if (request.getParameter("fIni") == null) { %> value="2023-01-01"
										<% } %>
										<% if(request.getParameter("fIni") != null || request.getParameter("fIni") != "") { %>
										value="<%= request.getParameter("fIni") %>"
										<% } %> >
										<label>Fecha final:</label>
										<input type="date" name="dateEnd" id="dateEnd" 
										<% if (request.getParameter("fIni") == null) { %> value="<%= LocalDate.now() %>"
										<% } %>
										<% if(request.getParameter("fFin") != null || request.getParameter("fFin") != "") { %>
										value="<%= request.getParameter("fFin") %>"
										<%  } %> >
									</div>
								</div>
								<div class="form-group col-md-4">
									<button type="submit" class="btn btn-sm bg-success text-white">Ver</button>
								</div>
							</form>
						</div>
						<% if(products.size() > 0) { %>
						<div>
							<table id="listadoProductos" style="margin-left: 30px; width: 500px;">
								<tr>
									<th style="text-align:center;">Producto</th>
									<th style="text-align: center;">Cantidad</th>
								</tr>
								<% for (Product p : products) { %>
								<tr>
									<td style="text-align: center"><%= p.getNombre() %></td>
									<td style="text-align:center;"><%= p.getNumeroUsado() %></td>
								</tr>
								<% } %>
							</table>
						</div>
						<% } %>
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