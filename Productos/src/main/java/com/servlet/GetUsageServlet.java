package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getUsage")
public class GetUsageServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String fechaInicio = req.getParameter("dateIni");
			String fechaFinal = req.getParameter("dateEnd");
			int userId = Integer.parseInt(req.getParameter("userId"));
			String texto = "viewProductsUse.jsp?id=" + userId;
			
			if(fechaInicio != null && fechaInicio != "") {
				texto = texto + "&fIni=" + fechaInicio; 
			}
			
			if (fechaFinal != null && fechaFinal != "") {
				texto = texto + "&fFin=" + fechaFinal;
			}
			
			System.out.println(texto);
			resp.sendRedirect(texto);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
