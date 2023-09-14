package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.ProductDAO;
import com.entity.Product;
import com.utils.Utils;

@WebServlet("/addProduct")
public class AddPostServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			ProductDAO productDAO = new ProductDAO(DBConnect.getConnection());
			
			String nombre = req.getParameter("name");
			String precioString = req.getParameter("price");
			precioString = Utils.pasarAFloat(precioString);
			Float precio = Float.parseFloat(precioString);
			int unidades = Integer.parseInt(req.getParameter("units"));
			int supermercado = Integer.parseInt(req.getParameter("supermercado"));
			
			List<Product> productos = productDAO.getProducts(); 
			
			Product p = new Product();
			p.setNombre(nombre);
			p.setPrecio(precio);
			p.setUnidades(unidades);
			p.setSupermercado(supermercado);
			p.setVersion(Utils.comprobarVersion(p, productos));
			
			HttpSession session = req.getSession();
			
			ProductDAO pDAO = new ProductDAO(DBConnect.getConnection());
			boolean f = pDAO.addProduct(p);
			
			if(f) {
				session.setAttribute("succMsg", "Product post successfully..");
				resp.sendRedirect("addProduct.jsp");
				
			} else {
				if(p.getVersion() == 0) {
					session.setAttribute("succMsg", "Product already exists on the BD");
				} else {
					session.setAttribute("succMsg", "Something wrong on server");
				}
				resp.sendRedirect("addProduct.jsp");
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
