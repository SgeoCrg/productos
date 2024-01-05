<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login page</title>
<%@include file="components/css.jsp" %>

</head>
<body>
<% session.setAttribute("userobj", null); %>
<%@include file="components/navbar.jsp" %>
	<div class="container-fuid">
		<div class="row p-5">
			<div class="col-md-4 offset-md-4">
				<div class="card">
					<div class="card-body">
						<div class="text-center">
							<i class="fa fa-user-plus fa-2x" aria-hidden="true"></i>
							<h5>Login page</h5>
						</div>
						<script>
						let formulario = document.getElementById('form');
						formulario.reset();
						</script>
<%-- <c:if test="${not empty succMsg }">
<h4 class="text-center text-danger">${succMsg }</h4>
<c:remove var="succMsg" />
</c:if> --%>
						<c:if test="${not empty succMsg }">
							<div class="alert alert-success" role="alert">${ succMsg }</div>
							<c:remove var="succMsg" />
						</c:if>
						<form id="form" action="login" method="post">

							<div class="form-group">
								<label>Enter mail</label> <input type="email"
									required="required" class="form-control"
									id="exampleInputEmail1" aria-describedby="emailHelp" name="email">
							</div>
							
							<div class="form-group">
								<label for="exampleInputEmail1">Enter Password</label> <input 
									required="required" type="password" class="form-control"
									id="exampleInputPassword1" name="password">
							</div>
							
							<button type="submit"
								class="btn btn-primary badge-pill btn-black">Login</button>
						</form>
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