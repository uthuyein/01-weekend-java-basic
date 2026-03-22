package com.jdc.mkt.test;

import static com.jdc.mkt.factory.MySqlConnector.getConnectionWith1String;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class JunitFactory {

	@BeforeAll
	static void init() {
		var sql = "truncate table data_tbl";

		try (var con = getConnectionWith1String(); 
				var stmt = con.createStatement()) {

			stmt.execute(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
