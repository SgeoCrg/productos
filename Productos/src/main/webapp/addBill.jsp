<%@ page import="com.dao.SupermarketDAO"%>
<%@ page import="com.dao.LineDAO"%>
<%@ page import="com.entity.Line"%>
<%@ page import="com.entity.Supermarket"%>
<%@ page import="com.dao.ProductDAO"%>
<%@ page import="com.entity.Product"%>
<%@ page import="com.DB.DBConnect"%>
<%@ page import="com.utils.Utils"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="components/css.jsp"%>
<title>Añadir factura</title>
<script type="text/javascript">
	function cargarProductosSupermercado() {
		var id = document.getElementById("supermercado").value;
		window.location.href = "/Productos/addBill.jsp?id=" + id;
	}
	
	function seleccionProducto() {
		var nombre = document.getElementById("name").value;
	}
	
</script>
</head>
<body style="background-color: #f0f1f2;">

	<% ProductDAO dao = new ProductDAO(DBConnect.getConnection()); 
	List<Product> productList = new ArrayList<>();
	if(request.getParameter("id") != null) {
		productList = dao.getProductsBySupermarket(Integer.parseInt(request.getParameter("id")));
		productList = Utils.soloUltimaVersion(productList);
		request.setAttribute("productos", productList);
	%>

	<datalist id="productName">

		<% for(Product p : productList) { %>
		<option value="<%= p.getNombre() %>"></option>

		<%}%>

	</datalist>

	<% }%>
	<%@include file="components/navbar.jsp"%>

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

						<h5>añadir factura</h5>
					</div>

					<% SupermarketDAO sDao = new SupermarketDAO(DBConnect.getConnection());
						List<Supermarket> listSupermarkets = sDao.getSupermarkets();
					%>
					<% List<Line> list = new ArrayList<Line>();
						if(request.getParameter("factura") != null) { 
						LineDAO lDao = new LineDAO(DBConnect.getConnection());
						list = lDao.getLinesForBillId(Integer.parseInt(request.getParameter("factura")));
						} %>

					<form action="addLine" method="post">
						<% if (request.getParameter("factura") == null) {%>
							<input id="esFacturaNueva" name="esFacturaNueva" value="true" type="hidden"/>
							<script>
								alert('aqui va la limpieza de lineas');
							</script>
						<% } else {%>
							<input id="esFacturaNueva" name="esFacturaNueva" value="false" type="hidden"/>
							<input id="facturaId" name="facturaId" type="hidden" value="<%= request.getParameter("factura") %>" />
						<% } %>
						<div class="form-row">
							<div class="form-group col-md-4">
							<div>
								<label>Supermercado</label> 
								
								<label style="margin-left:20px;">Fecha:</label><input id="fecha" name="fecha" type="date" 
								<% if (list.size() > 0) {  System.out.println(list.get(0).getFecha());%> disabled value="<%= list.get(0).formatearFecha() %>">
								<%System.out.println(list.get(0).getFecha());} %>
								
								<% if(list.size() > 0)  { System.out.println(list.get(0).getFecha());%>
								<input id="fechaOculta" name="fechaOculta" value="<%= list.get(0).formatearFecha()%>" type="hidden"
								<% } %>>
								
								<select name="supermercado"
									class="custom-select" id="supermercado"
									onchange="cargarProductosSupermercado()"
									<% if (request.getParameter("id") != null) {%> disabled <%} %>>

									<% if (request.getParameter("id") == null) {%>
									<option disabled selected>Choose...</option>
									<%} %>
									<% for(Supermarket s : listSupermarkets) { %>

									<% if((request.getParameter("id") != null) 
												&& (Integer.parseInt(request.getParameter("id")) == s.getId())) { %>
									<option value="<%=s.getId() %>"><%=s.getNombre() %></option>
									<%} else if (request.getParameter("id") == null) { %>
									<option value="<%=s.getId() %>"><%=s.getNombre() %></option>
									<% } %>

									<% } %>
								</select>
								<!-- <div class="form-group col-md-3">
								</div> -->
								</div>
								<input id="idSupermercado" name="idSupermercado" type="hidden"
									value="<%= request.getParameter("id")%>">
								<div class="form-group">
									<label>Enter name</label> <input type="text" list="productName"
										required="required" class="form-control form-control-sm"
										id="name" aria-describedby="emailHelp" name="name"
										onchange="seleccionProducto()"
										<% if (request.getParameter("id") != null) { %> autofocus
										<% } %>>
								</div>
								<div class="form-group">
									<label>Enter price</label> <input required="required"
										class="form-control form-control-sm" id="price"
										aria-describedby="emailHelp" name="price" value="">
								</div>
								<div class="form-group">
									<label>Enter units</label> <input required="required"
										class="form-control form-control-sm" id="exampleInputEmail1"
										aria-describedby="emailHelp" name="units">
								</div>
								<button class="btn btn-success">Añadir</button>
							</div>
					</form>
					<div class="form-group col-md-4" style="margin-left: 50px">
						<form action="updatePriceBill" method="post"><!-- addBill -->
						<% //List<Line> list = new ArrayList<Line>();
						//if(request.getParameter("factura") != null) { 
								//LineDAO lDao = new LineDAO(DBConnect.getConnection());
								//list = lDao.getLinesForBillId(Integer.parseInt(request.getParameter("factura")));
						//} %>
							<!-- <label>Fecha: </label><input id="fecha" name="fecha" type="date" 
								<% //if (list.size() > 0) { %> disabled <%//} %>>-->
							<input id="idfactura" name="idFactura" type="hidden" value="<%=request.getParameter("factura") %>" />
							<input id="idSupermercado" name="idSupermercado" type="hidden"
								value="<%= request.getParameter("id")%>">
							<table id="listadoProductos"
								style="margin-left: 30px; width: 500px;">
								<tr>
									<th style="text-align: center">Cantidad</th>
									<th style="width: 300px; text-align: center">Producto</th>
									<th style="text-align: center">Total</th>
								</tr>
								<% if (request.getParameter("factura") == null)  { %>
								<td colspan="3">Sin productos</td>
								<% } else {
											//LineDAO lDao= new LineDAO(DBConnect.getConnection()); 
											//List<Line> list = lDao.getLinesForBillId(Integer.parseInt(request.getParameter("factura")));
												float superTotal = 0;
												for (Line l : list) { %>
								<tr>
									<td style="text-align: right"><%= l.getCantidad() %></td>
									<td style="text-align: center; width: 300px;"><%= l.getNombreProducto() %></td>
									<td><%= l.getTotal() %></td>
								</tr>
								<% superTotal = superTotal + l.getTotal(); %>
								<% } %>
								<tr>
									<td colspan="3" style="text-align: right">Total: <%= superTotal %></td>
								</tr>
								<tr>
									<td colspan="3"><button class="btn btn-success"
											style="float: right">Finalizar</button></td>
									<input id="total" name="total" type="hidden"
										value="<%= superTotal %>">
								</tr>
								<% } %>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- </div>-->

	<div style="margin-top: 50px;">
		<%@include file="components/footer.jsp"%>
	</div>

</body>
</html>