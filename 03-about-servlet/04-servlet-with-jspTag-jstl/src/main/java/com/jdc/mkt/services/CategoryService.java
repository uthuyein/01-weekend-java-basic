package com.jdc.mkt.services;

import static com.jdc.mkt.utils.MySqlConnector.getConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jdc.mkt.entity.Category;

public class CategoryService implements CrudOperation<Category> {

	@Override
	public List<Category> selectAll() {
		var list = new ArrayList<Category>();
		String sql = "select * from category_tbl where is_active != 0";

		try (var con = getConnection(); var stmt = con.prepareStatement(sql)) {

			var rs = stmt.executeQuery();

			while (rs.next()) {
				var cat = new Category();
				cat.setId(rs.getInt("id"));
				cat.setName(rs.getString("name"));
				cat.setCreateDate(rs.getDate("create_date").toLocalDate());
				list.add(cat);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void insert(Category t) {
		String sql = "insert into category_tbl(name)values(?)";

		try (var con = getConnection(); 
			var stmt = con.prepareStatement(sql)) {
			stmt.setString(1, t.getName());
			stmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Category t) {
		String sql = "update category_tbl set name=? , is_active = ? where id = ?";
		try (var con = getConnection(); 
			var stmt = con.prepareStatement(sql)) {
			stmt.setString(1, t.getName());
			stmt.setBoolean(2, t.isActive());
			stmt.setInt(3, t.getId());
			stmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Optional<Category> findById(int id) {
		
		String sql = "select * from category_tbl where is_active != 0 and id = ?";
		var cat = new Category();
		
		try (var con = getConnection(); 
			var stmt = con.prepareStatement(sql)) {
			
			stmt.setInt(1, id);
			var rs = stmt.executeQuery();
			
			while (rs.next()) {
				cat.setId(rs.getInt("id"));
				cat.setName(rs.getString("name"));
				cat.setCreateDate(rs.getDate("create_date").toLocalDate());
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.of(cat);
	}

}
