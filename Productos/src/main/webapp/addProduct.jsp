<%@ page import="com.dao.SupermarketDAO" %>
<%@ page import="com.entity.Supermarket" %>
<%@ page import="com.DB.DBConnect" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Añadir Producto</title>
<%@include file="components/css.jsp" %>
</head>
<body style="background-color: #f0f1f2;">
	<%@include file="components/navbar.jsp" %>
	<%  boolean visibility= false; %>
	
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
						
						<h5>Añadir Producto</h5>
					</div>
					<% SupermarketDAO dao = new SupermarketDAO(DBConnect.getConnection()); 
					List<Supermarket> list = dao.getSupermarkets(); %>
					
					<form action="addProduct" method="post">
						<div class="form-row">
							<div class="form-group col-md-4">
								<label>Supermercado</label> 
								<select name="supermercado"
									class="custom-select" id="inlineFormCustomSelectPref"
									onchange="anularChoose()">
									<option name"=choose" disabled selected >Choose...</option>
									<% for(Supermarket s : list) { %>
									<option value="<%=s.getId() %>"><%=s.getNombre() %></option>
									<%} %>
								</select>
							</div>
							<div class="form-group col-md-6">
							</div>
							<div class="form-group">
								<label>Enter name</label>
								<input required="required" class="form-control form-control-sm"
									id="exampleInputEmail1" aria-describedby="emailHelp"
									name="name">
							</div>
							<div class="form-group">
								<label style="margin-left:10px; ">Enter price</label>
								<input required="required" class="form-control form-control-sm"
									id="exampleInputEmail1" aria-describedby="emailHelp"
									name="price" style="margin-left: 10px;" >
							</div>
							<div class="form-group">
								<label style="margin-left: 20px;" >Enter units</label>
								<input required="required" class="form-control form-control-sm"
									id="exampleInputEmail1" aria-describedby="emailHelp"
									name="units" style="margin-left: 20px;" >
							</div>
						</div>
						<button class="btn btn-success">Guardar</button>
					</form>
				</div>
			</div>
		</div>
	</div>	
	<div style="margin-top: 50px;">
		<%@include file="components/footer.jsp" %>
	</div>

</body>
</html>