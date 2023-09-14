<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-custom">
	<div class="container-fluid">
		<a class="navbar-brand" href="#">Navbar</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
					<a class="nav-link" href="index.jsp">Home 
						<span class="sr-only">(current)</span>
					</a>
				</li>

				<c:if test="${userobj.role eq 'admin' }">

					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href"#" id="navbarDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">
							<i class="fa fa-product-hunt" aria-hidden="true"></i> Productos 
						</a>
						
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="addProduct.jsp">
								<i class="fa fa-plus-circle"></i> Añadir Producto
							</a> 
							<a class="dropdown-item" href="editProduct.jsp">
								<i class="fa fa-pencil" aria-hidden="true"></i> Editar Producto 
							</a>
						</div>
					</li>

					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href"#" id="navbarDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">
							<i class="fa fa-barcode" aria-hidden="true"></i> Supermercados 
						</a>
						
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="addSupermarket.jsp">
								<i class="fa fa-plus-circle"></i> Añadir Supermercado 
							</a> 
							<a class="dropdown-item" href="editSupermarket.jsp">
								<i class="fa fa-pencil" aria-hidden="true"></i> Editar Supermercado
							</a>
						</div>
					</li>
				</c:if>
				
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">
						<i class="fas fa-file-invoice"></i> Facturas
					</a>
						
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="addBill.jsp">
							<i class="fa fa-plus-circle"></i> Añadir Factura 
						</a> 
						<a class="dropdown-item" href="editBill.jsp">
							<i class="fa fa-pencil" aria-hidden="true"></i> Editar Factura
						</a>
						<a class="dropdown-item" href="viewBills.jsp">
							<i class="fa fa-eye" aria-hidden="true"></i> Ver Factura
						</a>
					</div>
				</li>

				<!-- <li class="nav-item">
					<a class="nav-link" href="viewBills.jsp">
						<i class="fas fa-file-invoice"></i> Facturas 
					</a>
				</li>-->
			</ul>
		</div>
		<form class="form-inline my-2 my-lg-0">
			<a href="login.jsp" class="btn btn-light mr-1"><i
				class="fas fa-sign-in-alt"></i>LogIn</a> <a href="signup.jsp"
				class="btn btn-light"><i class="fas fa-user"></i>SignUp</a>
		</form>
	</div>
</nav>