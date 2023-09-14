package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entity.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String email=req.getParameter("email");
			String password=req.getParameter("password");
			User user = new User();
			HttpSession session = req.getSession();
			
			if("admin@gmail.com".equals(email) && "admin@121".equals(password)) {
				session.setAttribute("userobj", user);
				user.setRole("admin");
				resp.sendRedirect("admin.jsp");
				
			} else {
				
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
