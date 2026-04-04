package com.jdc.mkt;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/counter")
public class CounterServlet extends HttpServlet{

	
	private static final long serialVersionUID = -5301171510858908872L;
	private static final String COUNTER = "Counter";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		Request Scope
		Counter counter1 = (Counter) req.getAttribute(COUNTER);
		
		if(null == counter1) {
			counter1 = new Counter();
		}
		counter1.countUp();
		req.setAttribute(COUNTER, counter1);
		
//		Session Scope
		var session = req.getSession(true);
		Counter counter2 = (Counter)session .getAttribute(COUNTER);
		
		if(null == counter2) {
			counter2 = new Counter();
		}
		counter2.countUp();
		session.setAttribute(COUNTER, counter2);
		
//		Application Scope
		var context = getServletContext();
		Counter counter3 = (Counter)context .getAttribute(COUNTER);
		
		if(null == counter3) {
			counter3 = new Counter();
		}
		counter3.countUp();
		context.setAttribute(COUNTER, counter3);
		
		
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
