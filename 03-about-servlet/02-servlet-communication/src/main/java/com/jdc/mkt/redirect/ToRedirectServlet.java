package com.jdc.mkt.redirect;

import java.io.IOException;

import com.jdc.mkt.utils.Header;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/toRedirect")
public class ToRedirectServlet  extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private final String message = """
			<p>This is from ToRedirect.</p>
			<a href="index.html">Home</a>
			</div>
			</div>
			</body>
			</html>
			""";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mess = (String) req.getAttribute("message");
		if(mess == null) {
			mess = "<p class='text-danger'>There is no attribute</p>";
		}
		resp.getWriter()
		.append(Header.HEAD)
		.append(mess)
		.append(message);
	}
}
