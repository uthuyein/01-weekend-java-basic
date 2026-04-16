package com.jdc.mkt.controller;

import java.io.IOException;
import java.time.LocalDate;

import com.jdc.mkt.entity.Category;
import com.jdc.mkt.services.CategoryService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({ "/category/categories", "/category/addCategory", "/category/updateCategory", "/category/deleteCategory" })
public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CategoryService service;

	@Override
	public void init() throws ServletException {
		service = new CategoryService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		switch (req.getServletPath()) {
		case "/category/deleteCategory":
			doPost(req, resp);
			break;
		case "/category/updateCategory":
			req.setAttribute("category", checkCategory(req));
			break;
		}

		req.setAttribute("categories", service.selectAll());
		req.getRequestDispatcher("/category/categories.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var name = req.getParameter("name");
		var category = checkCategory(req);
		
		if (category == null) {
			service.insert(getCategory(name));

		} else {
			category.setActive(req.getServletPath().endsWith("/deleteCategory") ? false : true);
			category.setName(category.isActive()?name:category.getName());
			service.update(category);
		}

		req.setAttribute("categories", service.selectAll());
		req.getRequestDispatcher("/category/categories.jsp").forward(req, resp);
	}

	private Category checkCategory(HttpServletRequest req) {
		var id = req.getParameter("id");
		if (null != id) {
			var catId = Integer.parseInt(id);
			var cat = service.findById(catId).orElse(null);
			return cat;
		}
		return null;
	}

	private Category getCategory(String name) {
		try {
			if(null == name) {
				throw new RuntimeException("Type Category name again !");
			}
			var cat = new Category();
			cat.setName(name);
			cat.setActive(true);
			cat.setCreateDate(LocalDate.now());
			return cat;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
