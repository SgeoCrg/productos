package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.BillDAO;
import com.dao.LineDAO;
import com.dao.ProductDAO;
import com.dao.SupermarketDAO;
import com.entity.Bill;
import com.entity.Line;
import com.entity.Product;
import com.entity.User;
import com.utils.Utils;

@WebServlet("/addLine")
public class AddLineServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			int idE;
			ProductDAO pDao = new ProductDAO(DBConnect.getConnection());
			int idSupermercado = Integer.parseInt(req.getParameter("idSupermercado"));
			List<Product> productos = pDao.getProductsBySupermarket(idSupermercado);
			productos = Utils.soloUltimaVersion(productos);
			
			BillDAO bDao = new BillDAO(DBConnect.getConnection());
			
			int id = bDao.getSiguienteId();
			int idFactura;
			//COMPROBAR A PARTIR DE ES FACTURA NUEVA
			if(Boolean.parseBoolean(req.getParameter("esFacturaNueva")))
				idFactura = id;
			else idFactura = Integer.parseInt(req.getParameter("facturaId"));
			
			String producto = req.getParameter("name");
			String precioString = Utils.pasarAFloat(req.getParameter("price"));
			float precio = Float.parseFloat(precioString);
			int cantidad = Integer.parseInt(req.getParameter("units"));
			float total = precio * cantidad;
			String fecha;
			boolean esFacturaNueva = Boolean.parseBoolean(req.getParameter("esFacturaNueva"));
			
			Product newProduct = new Product();

			newProduct.setNombre(producto);
			newProduct.setPrecio(precio);
			newProduct.setSupermercado(idSupermercado);
			newProduct.setUnidades(1);
			newProduct.setVersion(Utils.comprobarVersion(newProduct, productos));
			
			if(newProduct.getVersion() != 0) {
				System.out.println("Se va a agregar el siguiente producto: " + newProduct.getNombre());
				pDao.addProduct(newProduct);
				idE = pDao.getUltimaId();
			} else {
				idE = pDao.getIdByName(newProduct.getNombre(), newProduct.getPrecio(), newProduct.getSupermercado());
			}
			
			HttpSession session = req.getSession(); 
			
			/*ID, SUPERMERCADO y FECHA*/
			if(esFacturaNueva) {
				Bill b = new Bill();
				
				b.setSupermercado(idSupermercado);
				if(req.getParameter("fecha") != null) {
					System.out.println(req.getParameter("fecha"));
					//fecha = Utils.formatearFechaInversa(req.getParameter("fecha"));
					fecha = req.getParameter("fecha");
					System.out.println(fecha);
				} else {
					//fecha = Utils.formatearFechaInversa(req.getParameter("fechaOculta"));
					fecha = req.getParameter("fecha");
				}
				b.setFecha(fecha);
				int idUsuario = ((User)session.getAttribute("userobj")).getId();
				
				bDao.addBill(b, idUsuario);
			}
			
			LineDAO lDAO = new LineDAO(DBConnect.getConnection());
			
			Line l = new Line(idFactura, idE, cantidad, total);
			if(req.getParameter("fecha") != null) {
				//fecha = Utils.formatearFechaInversa(req.getParameter("fecha"));
				fecha = req.getParameter("fecha");
			} else {
				//fecha = Utils.formatearFechaInversa(req.getParameter("fechaOculta"));
				fecha = req.getParameter("fechaOculta");
			}
			l.setFecha(fecha);
			
			boolean f = lDAO.addLine(l);
			
			if(f) {
				session.setAttribute("succMsg", "line post successfully..");
				resp.sendRedirect("addBill.jsp?id=" + idSupermercado + "&factura=" + idFactura);
				
			} else {
				session.setAttribute("succMsg", "Something wrong on server");
			}
			
			resp.sendRedirect("addBill.jsp?id=" + idSupermercado + "&factura=" + idFactura);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
