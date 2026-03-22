package com.jdc.mkt.test;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import static com.jdc.mkt.factory.MySqlConnector.*;

public class A_JDBC_Connection_Test {
	
	@Test
	void test() {
		try (var con = getConnectionWithProps(); 
				var stmt = con.createStatement()) {
			
			stmt.executeUpdate("insert into data_tbl(name,age) values('Andrew',23)");
	
			System.out.println("Successfully save !");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
