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
import com.dao.SupermarketDAO;
import com.entity.Supermarket;
import com.utils.Utils;

@WebServlet("/addSupermarket")
public class AddMarketServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			SupermarketDAO supermarketDAO = new SupermarketDAO(DBConnect.getConnection());
			
			String nombre = req.getParameter("name");
			String direccion = req.getParameter("address");
			
			List<Supermarket> supermercados = supermarketDAO.getSupermarkets();
			
			Supermarket s = new Supermarket();
			
			s.setNombre(nombre);
			s.setDireccion(direccion);
			s.setVersion(Utils.comprobarVersion(s, supermercados));
			
			HttpSession session = req.getSession();
			
			SupermarketDAO sDAO = new SupermarketDAO(DBConnect.getConnection());
			
			boolean f = sDAO.addSuperMarket(s);

			if(f) {
				session.setAttribute("succMsg", "Supermarket post successfully..");
				resp.sendRedirect("addSupermarket.jsp");
				
			} else {
				if(s.getVersion() == 0) {
					session.setAttribute("succMsg", "Supermarket already exists on the BD");
				} else {
					session.setAttribute("succMsg", "Something wrong on server");
				}
				resp.sendRedirect("addSupermarket.jsp");
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}