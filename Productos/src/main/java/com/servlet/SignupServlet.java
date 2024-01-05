package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.UserDAO;
import com.entity.User;

@WebServlet("/register")
public class SignupServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {			
			HttpSession session = req.getSession(); 
			User newUser = new User();
			
			newUser.setName(req.getParameter("name"));
			newUser.setEmail(req.getParameter("email"));
			newUser.setPassword(req.getParameter("password"));
			
			UserDAO uDAO = new UserDAO(DBConnect.getConnection());
			
			boolean f = uDAO.register(newUser);

			if(f) {
				session.setAttribute("succMsg", "user created successfully..");
				resp.sendRedirect("login.jsp");
				
			} else {
				session.setAttribute("succMsg", "Something wrong on server");
				resp.sendRedirect("signup.jsp");
			}
			
			resp.sendRedirect("signup.jsp");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
