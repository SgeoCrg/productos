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

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		ProductDAO pDAO = new ProductDAO(DBConnect.getConnection());
		boolean f = pDAO.deleteProduct(id);
		
		HttpSession session = req.getSession();
		
		if (f) {
			session.setAttribute("succMsg", "Product deleted successfully..");

		} else {
			session.setAttribute("succMsg", "Something wrong on server");
		}

		resp.sendRedirect("editProduct.jsp");
	}

}
