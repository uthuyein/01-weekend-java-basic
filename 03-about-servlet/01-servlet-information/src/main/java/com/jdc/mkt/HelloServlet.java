package com.jdc.mkt;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/hello",
loadOnStartup = 1,
name = "welcome" ,
initParams = {
		@WebInitParam(name="course",value = "Core java")
		})
public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("attribute", "This is test attribute.");
		
		
		var headers = req.getHeaderNames(); //Http:
		var paramters = req.getParameterNames(); //req parameter string
		var sParams = getInitParameterNames();//servelet
		var attributes =  req.getAttributeNames();//req attributes object
		
		while(headers.hasMoreElements()) {
			var header = headers.nextElement();
			System.out.println(req.getHeader(header));
		}
		
		while(paramters.hasMoreElements()) {
			var param = paramters.nextElement();
			System.out.println(param+"\t" +req.getParameter(param));
		}
		
		while(sParams.hasMoreElements()) {
			var param = sParams.nextElement();
			System.out.println(param+"\t" +getInitParameter(param));
		}
		
		while(attributes.hasMoreElements()) {
			var param = attributes.nextElement();
			System.out.println(param+"\t" +req.getAttribute(param));
		}
		
		
		
		resp.getWriter()
		.append("<h1> Hello Servlet</h1>")
		.append("<a href='/'>Home</a>")
		.flush();
	}
}
