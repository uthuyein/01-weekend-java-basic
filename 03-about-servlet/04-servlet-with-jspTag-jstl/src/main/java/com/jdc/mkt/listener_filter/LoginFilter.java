package com.jdc.mkt.listener_filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = {"/category/*","/product/*"})
public class LoginFilter  implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		var session = req.getSession();
		var loginId = session.getAttribute("loginId");
//		var password = session.getAttribute("password");
		
		if (null != session && (null != loginId && loginId.equals(loginId))) {
			chain.doFilter(request, response);
		} else {
			req.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
	}

}
