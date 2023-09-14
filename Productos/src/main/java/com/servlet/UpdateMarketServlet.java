package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.SupermarketDAO;
import com.entity.Supermarket;

@WebServlet("/editSupermarket")
public class UpdateMarketServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			String nombre = req.getParameter("name");
			String direccion = req.getParameter("address");
			int id = Integer.parseInt(req.getParameter("id"));

			Supermarket s = new Supermarket();

			s.setNombre(nombre);
			s.setDireccion(direccion);
			s.setId(id);

			SupermarketDAO sDAO = new SupermarketDAO(DBConnect.getConnection());

			HttpSession session = req.getSession();

			boolean f = sDAO.editSupermarket(s);

			if (f) {
				session.setAttribute("succMsg", "Supermarket update successfully..");

			} else {
				session.setAttribute("succMsg", "Something wrong on server");
			}

			resp.sendRedirect("editSupermarket.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
