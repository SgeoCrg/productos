package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.ProductDAO;
import com.entity.Product;

@WebServlet("/editProduct")
public class UpdateServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			String nombre = req.getParameter("name");
			float precio = Float.parseFloat(req.getParameter("price"));
			int unidades = Integer.parseInt(req.getParameter("units"));
			int id = Integer.parseInt(req.getParameter("id"));

			Product p = new Product();

			p.setNombre(nombre);
			p.setPrecio(precio);
			p.setUnidades(unidades);
			p.setId(id);

			ProductDAO pDAO = new ProductDAO(DBConnect.getConnection());

			HttpSession session = req.getSession();

			boolean f = pDAO.editProduct(p);

			if (f) {
				session.setAttribute("succMsg", "Product update successfully..");

			} else {
				session.setAttribute("succMsg", "Something wrong on server");
			}

			resp.sendRedirect("editProduct.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
