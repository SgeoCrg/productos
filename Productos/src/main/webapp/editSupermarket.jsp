<%@ page import="com.dao.SupermarketDAO" %>
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
<title>Editar Supermercado</title>
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
<% SupermarketDAO dao = new SupermarketDAO(DBConnect.getConnection()); 
	Supermarket editS = new Supermarket();
	if(request.getParameter("id") != null) {
	editS = dao.getSuperMarketById(Integer.parseInt(request.getParameter("id")));}
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
						
						<h5>Editar Supermercado</h5>
					</div>
							<div>
								<label>Supermercados</label>
							</div>
					<div>
						<% List<Supermarket> list = dao.getSupermarkets(); %>
						<form action="editSupermarket" method="post">
							<div class="form-row">
								<div class="form-group col-md-4"  style="overflow:scroll; height:200px; width:700px;">
									<!-- <label>Supermercados</label>-->
									<table style="border-collapse: collapse; border:3px solid purple; width:100%;">
										<tr>
											<th class="text-center" style="width:30%">Nombre</th>
											<th class="text-center">Dirección</th>
											<th></th>
										</tr>
										<% for(Supermarket s : list) { %>
										<tr class="col-md-10">
											<td class="text-center celdaAsignado"><a href="editSupermarket.jsp?id=<%=s.getId() %>" style="color:black; text-decoration:none;"><%= s.getNombre()%></a></td>
											<td class="text-center"><a href="editSupermarket.jsp?id=<%=s.getId() %>"style="color:black; text-decoration:none;"><%= s.getDireccion()%></a></td>
											<td><a href="delete?id=<%=s.getId() %>" class="btn delete-img"></a></td>
										</tr>
										<% } %>
									</table>
								</div>
								<div class="form-group col-md-6">
								</div>
								<div class="form-row">
									<div class="form-group">
										<label>Nombre</label>
											<% if (editS.getNombre() == null) {%>
										<input required="required" class="form-control form-control-sm"
											id="exampleInputEmail1" aria-describedby="emailHelp"
											name="name" value = "">
											<% }else{ %>
										<input required="required" class="form-control form-control-sm"
											id="exampleInputEmail1" aria-describedby="emailHelp"
											name="name" value="<%= editS.getNombre() %>">
											<%} %>
									</div>
									<div class="form-group">
										<label style="margin-left:10px; ">Direccion</label>
											<% if (editS.getNombre() == null) {%>
										<input required="required" class="form-control form-control-sm"
											id="exampleInputEmail1" aria-describedby="emailHelp"
											name="address" style="margin-left: 10px;" value = "">
											<% }else{ %>
										<input required="required" class="form-control form-control-sm"
											id="exampleInputEmail1" aria-describedby="emailHelp"
											name="address" style="margin-left: 10px;" value="<%= editS.getDireccion() %>">
											<%} %>
									</div>
										<input type="hidden" name="id" value="<%= editS.getId() %>">
								</div>
							</div>
							<% if(editS.getNombre() != null) { %>
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