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

@WebServlet("/deleteSupermarket")
public class DeleteSupermarketServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		SupermarketDAO sDAO = new SupermarketDAO(DBConnect.getConnection());
		boolean f = sDAO.deleteSupermarket(id);
		
		HttpSession session = req.getSession();
		
		if (f) {
			session.setAttribute("succMsg", "Supermarket deleted successfully..");

		} else {
			session.setAttribute("succMsg", "Something wrong on server");
		}

		resp.sendRedirect("editSupermarket.jsp");
	}
}
