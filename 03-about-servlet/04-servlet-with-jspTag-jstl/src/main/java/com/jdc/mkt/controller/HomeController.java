package com.jdc.mkt.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/",loadOnStartup = 1)
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String loginId = "admin@gmail.com";
	private String password =  "123";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var session = req.getSession(false);
		System.out.println(session);
		var name = "login.jsp";
			
		if(null != session && (null != session.getAttribute("loginId") && session.getAttribute("loginId").equals(loginId))) {
			name = "/products/product-list.jsp";
		}
		
		req.getRequestDispatcher(name).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var name = req.getParameter("email");
		var pass = req.getParameter("password");
		var check = req.getParameter("saveId");
		
		try {
			if(null == name || !name.equals(loginId)) {
				throw new RuntimeException("LoginId Invalid");
			}
			if(null == pass || !pass.equals(password)) {
				throw new RuntimeException("Password try again !");
			}
			
			if(null != check) {		
				HttpSession session =req.getSession(true);
				session.setAttribute("loginId", name);
				session.setAttribute("password", pass);
			}
			
		}catch (Exception e) {
			req.setAttribute("message", e.getMessage());
			System.out.println(e.getMessage());
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
		
		req.getRequestDispatcher("/products/product-list.jsp").forward(req, resp);
	}
}
