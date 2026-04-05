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

@WebServlet({ "/categories", "/addCategory", "/updateCategory", "/deleteCategory" })
public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CategoryService service;

	@Override
	public void init() throws ServletException {
		service = new CategoryService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var id = req.getParameter("id");
		var name = req.getParameter("name");
			System.out.println("ID :"+id);
		if (null != id) {
			var c = getCategory(name);
			c.setId(Integer.parseInt(id));
			c.setActive(false);
			service.update(c);
		}
	
		req.setAttribute("categories", service.selectAll());
		req.getRequestDispatcher("/categories/category-list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		var id = req.getParameter("id");
		var name = req.getParameter("name");

		if (null != id) {
			var c = getCategory(name);
			c.setId(Integer.parseInt(id));

			switch (req.getServletPath()) {

				case "/updateCategory": {
					c.setActive(true);
					break;
				}
				case "/deleteCategory": {
					c.setActive(false);
					break;
				}
			}
			service.update(c);
		}

		if (null != name) {
			service.insert(getCategory(name));
		}
		resp.sendRedirect(getServletContext().getContextPath().concat("/categories"));

	}

	private Category getCategory(String name) {
		var cat = new Category();
		cat.setName(name);
		cat.setActive(true);
		cat.setCreateDate(LocalDate.now());
		return cat;

	}

}
