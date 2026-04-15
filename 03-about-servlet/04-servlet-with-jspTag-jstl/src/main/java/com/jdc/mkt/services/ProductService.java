package com.jdc.mkt.services;

import static com.jdc.mkt.utils.MySqlConnector.getConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jdc.mkt.entity.Category;
import com.jdc.mkt.entity.Product;

public class ProductService implements CrudOperation<Product> {

	@Override
	public List<Product> selectAll() {
		var list = new ArrayList<Product>();
		String sql = """
				select p.id,p.name,p.price,p.create_date,c.id,c.name,c.create_date from product_tbl p
				join category_tbl c on p.category_id = c.id where p.is_active != 0
				""";

		try (var con = getConnection(); var stmt = con.prepareStatement(sql)) {

			var rs = stmt.executeQuery();

			while (rs.next()) {
				list.add( createProduct(rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void insert(Product t) {
		String sql = "insert into product_tbl(name,price,category_id)values(?,?,?)";

		try (var con = getConnection(); 
			var stmt = con.prepareStatement(sql)) {
			stmt.setString(1, t.getName());
			stmt.setDouble(2, t.getPrice());
			stmt.setInt(3, t.getCategory().getId());
			stmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Product t) {
		String sql = "update product_tbl set name=? , price = ?,category_id = ?, is_active = ? where id = ?";
		try (var con = getConnection(); 
			var stmt = con.prepareStatement(sql)) {
			stmt.setString(1, t.getName());
			stmt.setDouble(2, t.getPrice());
			stmt.setInt(3, t.getCategory().getId());
			stmt.setBoolean(4, t.isActive());
			stmt.setInt(5, t.getId());
			stmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Optional<Product> findById(int id) {
		
		String sql = """
				select p.id,p.name,p.price,p.create_date,c.id,c.name,c.create_date from product_tbl p
				join category_tbl c on p.category_id = c.id where p.is_active != 0 and p.id = ?
				""";;
		var p = new Product();
		
		try (var con = getConnection(); 
			var stmt = con.prepareStatement(sql)) {
			
			stmt.setInt(1, id);
			var rs = stmt.executeQuery();
			
			while (rs.next()) {
				p = createProduct(rs);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.of(p);
	}
	
	private Product createProduct(ResultSet rs) throws SQLException {
		var p = new Product();
		var c = new Category();
		c.setId(rs.getInt("c.id"));
		c.setName(rs.getString("c.name"));
		c.setCreateDate(rs.getDate("c.create_date").toLocalDate());
		
		p.setCategory(c);
		
		p.setId(rs.getInt("p.id"));
		p.setName(rs.getString("p.name"));
		p.setPrice(rs.getDouble("p.price"));
		p.setCreateDate(rs.getDate("p.create_date").toLocalDate());
		return p;
	}

}
