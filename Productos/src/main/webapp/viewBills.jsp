<%@ page import="com.dao.SupermarketDAO"%>
<%@ page import="com.dao.BillDAO"%>
<%@ page import="com.dao.LineDAO"%>
<%@ page import="com.entity.Supermarket"%>
<%@ page import="com.entity.Bill"%>
<%@ page import="com.entity.Line"%>
<%@ page import="com.entity.User" %>
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
<title>Ver Facturas</title>
<script type="text/javascript">
	function cargarFacturasSupermercado() {
		var id = document.getElementById("supermercado").value;
		window.location.href = "/Productos/viewBills.jsp?id=" + id;
	}
	
</script>
</head>
<body style="background-color: #f0f1f2;">
	<%@include file="components/navbar.jsp"%>

	<% BillDAO bDAO = new BillDAO(DBConnect.getConnection());
	List<Bill> bills = new ArrayList<Bill>();
	int userId = ((User)session.getAttribute("userobj")).getId();//Integer.parseInt(session.getAttribute("userobj").toString().split(":")[0]);
	if(request.getParameter("id") == null || Integer.parseInt(request.getParameter("id")) == 0) {
		bills = bDAO.getAllBills(userId);
	} else {
		bills = bDAO.getBillsBySupermarket(Integer.parseInt(request.getParameter("id")), userId);
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

						<h5>buscar factura</h5>
					</div>
					<% SupermarketDAO dao = new SupermarketDAO(DBConnect.getConnection()); 
					List<Supermarket> list = dao.getSupermarkets(); %>

					<div class="form-row">
						<div class="form-group col-md-4">
							<label>Supermercado</label> <select name="supermercado"
								class="custom-select" id="supermercado"
								onchange="cargarFacturasSupermercado()">
								<% if (request.getParameter("id") == null 
											|| Integer.parseInt(request.getParameter("id")) == 0) {%>
								<option selected value="0">Todos</option>
								<% } else { %>
								<option value="0">Todos</option>
								<% } %>
								<% for(Supermarket s : list) { %>
								<% if((request.getParameter("id") != null) 
											&& (Integer.parseInt(request.getParameter("id")) == s.getId())) { %>
								<option value="<%=s.getId() %>" selected><%=s.getNombre() %></option>
								<%} else { %>
								<option value="<%=s.getId() %>"><%=s.getNombre() %></option>
								<% } %>
								<%} %>
							</select>
							<table id="listadoFacturas" style="width: 300px;">
								<tr>
									<th style="text-align: center">Fecha</th>
									<th style="width: 300px; text-align: center">Supermercado</th>
									<th style="text-align: center">Total</th>
									<th></th>
								</tr>
								<% 
							for (Bill b : bills) { %>
								<tr>
									<td style="text-align: right"><%= b.getFecha() %></td>
									<td style="text-align: center; width: 300px;"><%= b.getNombreSupermercado() %></td>
									<td><%= b.getTotal() %></td>
									<% String texto = "viewBills.jsp?";
									if(request.getParameter("id") != null) {
										texto = texto + "id=" + request.getParameter("id") +"&";
									}
									texto = texto + "factura=" + b.getId();
									System.out.println(texto);%>
									<td><a href="<%= texto %>"><i class="fa fa-eye"
											aria-hidden="true"></i></a></td>
								</tr>
								<% } %>
							</table>
						</div>
						<div class="form-group col-md-7" style="margin-left: 50px; overflow:scroll; height:600px; width:500px;">
							<form action="addBill" method="post">
								<input id="idSupermercado" name="idSupermercado" type="hidden"
									value="<%= request.getParameter("id")%>">
								<table id="listadoProductos" style="margin-left: 30px; width: 500px;">
									<tr>
										<th style="text-align:center; width:15%;">Cantidad</th>
										<th style="width:300px; text-align: center;">Producto</th>
										<th style="text-align: left;">Total</th>
									</tr>
									<% if (request.getParameter("factura") != null)  {  
										float superTotal = 0;
										LineDAO lDao= new LineDAO(DBConnect.getConnection()); 
										List<Line> lineasList = lDao.getLinesForBillId(Integer.parseInt(request.getParameter("factura")));
										for (Line l : lineasList) { %>
									<tr>
										<td style="text-align: right"><%= l.getCantidad() %></td>
										<td style="text-align: center; width: 300px;"><%= l.getNombreProducto() %></td>
										<td><%= l.getTotal() %></td>
									</tr>
									<% superTotal = superTotal + l.getTotal(); %>
									<% } %>
									<tr>
										<td></td>
										<td style="text-align:right;">Total:  </td>
										<td style="text-align:left;"><%= Math.round(superTotal*100d)/100d %></td>
									</tr>
									<% } %>
								</table>
							</form>
						</div>

					</div>

				</div>
			</div>
		</div>
	</div>

	<div style="margin-top: 50px;">
		<%@include file="components/footer.jsp"%>
	</div>

</body>
</html>