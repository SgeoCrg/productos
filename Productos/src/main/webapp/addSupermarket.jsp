<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Añadir supermercado</title>
<%@include file="components/css.jsp" %>
</head>
<body style="background-color: #f0f1f2;">
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
						
						<h5>Añadir Supermercado</h5>
					</div>
					<form action="addSupermarket" method="post">
						<!-- <div class="form-group">
							<label>Enter Name Product</label> <input type="text" name="name"
								required class="form-control">
						</div> -->
						<div class="form-group col-md-4">
							<label>Enter name</label>
							<input required="required" class="form-control"
								id="exampleInputEmail1" aria-describedby="emailHelp"
								name="name">
						</div>
						<div class="form-group">
							<label>Enter address</label>
							<textarea required rows="3" cols="" name="address"
								class="form-control"></textarea>
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