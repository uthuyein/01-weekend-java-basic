package com.jdc.mkt.test;

import static com.jdc.mkt.utils.MySqlConnector.getConnection;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;

public class JunitFactory {

	
	private static List<String> list = List.of(
			"SET FOREIGN_KEY_CHECKS = 0",
			"truncate table category_tbl",
			"truncate table product_tbl",
			"INSERT INTO category_tbl(name,category_id) VALUES('Dry Fruits',1)",
			"INSERT INTO category_tbl(name,category_id) VALUES('Fresh Fruits',1)",
			"INSERT INTO category_tbl(name,category_id) VALUES('Hot Drinks',2)",
			"INSERT INTO category_tbl(name,category_id) VALUES('Cold Drinks',2)",
			"INSERT INTO product_tbl(name,price,category_id) VALUES('Banana',1200.00,4)",
			"INSERT INTO product_tbl(name,price,category_id) VALUES('Orange',1500.00,4)",
			"SET FOREIGN_KEY_CHECKS = 1"
			);
	
	@BeforeAll
	static void init() {
		try(var con = getConnection();
			var stmt = con.createStatement()){
			
			for(String str: list) {
				stmt.addBatch(str);
			}
			
			stmt.executeLargeBatch();
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
