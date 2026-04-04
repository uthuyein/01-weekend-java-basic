package com.jdc.mkt.forward;

import java.io.IOException;

import com.jdc.mkt.utils.Header;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns =  "/toForward",name = "toForwardName")
public class ToForward extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private final String message = """
			<p>This is from ToForward.</p>
			<a href="index.html">Home</a>
			</div>
			</div>
			</body>
			</html>
			""";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mess = (String) req.getAttribute("message");
		
		resp.getWriter()
		.append(Header.HEAD)
		.append(mess)
		.append(message);
		
	}
}
