package com.jdc.mkt.include;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/include")
public class IncludeServlet extends HttpServlet{
	
	private final String INCLUDE="""
			<p>This is from Include.</p>
			<a href="index.html">Home</a>
			</div>
			</div>
			</body>
			</html>
			""";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.
		getRequestDispatcher("/toInclude").include(req, resp);
		resp.getWriter()
		.append(INCLUDE);
	}

}
