package com.jdc.mkt.controller;

import java.io.IOException;

import com.jdc.mkt.entity.Category;
import com.jdc.mkt.entity.Product;
import com.jdc.mkt.services.CategoryService;
import com.jdc.mkt.services.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/product/products", "/product/addProduct", "/product/updateProduct",
		"/product/deleteProuct" })
public class ProductController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private CategoryService catService;
	private ProductService prodService;

	@Override
	public void init() throws ServletException {
		catService = new CategoryService();
		prodService = new ProductService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("categories", catService.selectAll());
		req.setAttribute("products", prodService.selectAll());

		switch (req.getServletPath()) {
		case "/product/deleteProduct":
			doPost(req, resp);
			break;
		case "/product/updateProduct":
			req.setAttribute("product", checkProduct(req));
			req.getRequestDispatcher("/product/addProduct.jsp").forward(req, resp);
			break;
		case "/product/addProduct":
			req.getRequestDispatcher("/product/addProduct.jsp").forward(req, resp);
			break;
		}
		req.getRequestDispatcher("/product/products.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		var name = req.getParameter("name");
		var price = req.getParameter("price");
		var cat_id = req.getParameter("cat_id");

		var product = checkProduct(req);
		
		if (product == null) {
			prodService.insert(getProduct(name, price, cat_id));

		} else {
			if (req.getServletPath().endsWith("/addProduct")) {
				
				System.out.println("Product ::: "+product);

				var p = getProduct(name, price, cat_id);
				
				product.setName(p.getName());
				product.setPrice(p.getPrice());
				product.setCategory(p.getCategory());
				product.setActive(true);
				
			} else {
				product.setActive(false);

			}
			prodService.update(product);
			
		}

		req.setAttribute("categories", prodService.selectAll());
		req.setAttribute("products", prodService.selectAll());

		req.getRequestDispatcher("/product/products.jsp").forward(req, resp);
	}

	private Product checkProduct(HttpServletRequest req) {
		var id = req.getParameter("id");
		if (null != id && !id.isEmpty()) {
			var pId = Integer.parseInt(id);
			var p = prodService.findById(pId).orElse(null);
			return p;
		}
		return null;
	}

	private Product getProduct(String name, String price, String cat_id) {
		try {
			if (null == name) {
				throw new RuntimeException("Type Product Name !");
			}
			if (null == price && name.isEmpty()) {
				throw new RuntimeException("Type Product Price !");
			}
			if (null == cat_id) {
				throw new RuntimeException("Select Category One !");
			}

			var cat = new Category();
			cat.setId(Integer.parseInt(cat_id));
			var p = new Product();
			p.setCategory(cat);
			p.setName(name);
			p.setPrice(Double.parseDouble(price));
			return p;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
