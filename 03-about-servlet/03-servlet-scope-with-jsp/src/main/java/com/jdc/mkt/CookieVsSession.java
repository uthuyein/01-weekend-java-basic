package com.jdc.mkt;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cookie")
public class CookieVsSession extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var session = req.getSession(true);
		session.setAttribute("password", "123");
		
		var cookie = new Cookie("loginId", "admin@gmail.com");
		cookie.setMaxAge(10);
		
		resp.addCookie(cookie);
		
		
		resp.sendRedirect("cookie.jsp");
	}
}
