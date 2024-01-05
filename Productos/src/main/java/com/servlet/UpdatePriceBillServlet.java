package com.servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.BillDAO;
import com.entity.Bill;

@WebServlet("/updatePriceBill")//addBill
public class UpdatePriceBillServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			BillDAO bDao = new BillDAO(DBConnect.getConnection());
			//int idSupermercado = Integer.parseInt(req.getParameter("idSupermercado"));
			int facturaId = Integer.parseInt(req.getParameter("idFactura"));
			float total = Float.parseFloat(req.getParameter("total"));
			/*Bill newBill  = new Bill();
			
			if(req.getParameter("fecha") == null || req.getParameter("fecha").equals("")) {
				Calendar fecha = Calendar.getInstance();
				String fechaHoy = "" + fecha.get(Calendar.DATE) + "/" + fecha.get(Calendar.MONTH) + "/" + fecha.get(Calendar.YEAR);
				newBill.setFecha(fechaHoy);
			} else {
				newBill.setFecha(req.getParameter("fecha").toString());
			}
			newBill.setSupermercado(idSupermercado);
			newBill.setTotal(Float.parseFloat(req.getParameter("total")));*/
			
			HttpSession session = req.getSession(); 
			
			boolean f = bDao.updateBill(facturaId, total);
			
			if(f) {
				session.setAttribute("succMsg", "bill post successfully..");
				resp.sendRedirect("addBill.jsp");
				
			} else {
				session.setAttribute("succMsg", "Something wrong on server");
				resp.sendRedirect("addBill.jsp");
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
