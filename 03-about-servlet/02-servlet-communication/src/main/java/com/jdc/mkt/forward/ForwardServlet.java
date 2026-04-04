package com.jdc.mkt.forward;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/forward")
@SuppressWarnings("serial")
public class ForwardServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		getServletContext().getRequestDispatcher("/toForward").forward(req, resp);
//		getServletContext().getNamedDispatcher("toForwardName").forward(req, resp);
		req.setAttribute("message", "<p class='text-danger'> from forward attribute </p>");
		req.getRequestDispatcher("/toForward").forward(req, resp);
	}
}
