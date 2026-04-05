package com.jdc.mkt.listener_filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter("/counter")
public class CounterFilter implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest seq = (HttpServletRequest) req;
		var dat = seq.getParameter("data");
		
		if(null != dat && dat.equals("data1")) {
			System.out.println("This is data1");
			chain.doFilter(req, resp);
		}else {
			req.getRequestDispatcher("cookie.jsp").forward(seq, resp);
		}
		
	}

}
