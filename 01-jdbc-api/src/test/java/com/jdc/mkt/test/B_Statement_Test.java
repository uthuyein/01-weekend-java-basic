package com.jdc.mkt.test;

import java.sql.SQLException;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static com.jdc.mkt.factory.MySqlConnector.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class B_Statement_Test {

	@ParameterizedTest
	@CsvSource({ "John,12", "Michael Bannett,65" })
	void insertTest(String name, int age) {

		var sql = "insert into data_tbl(name,age) values('%s',%d)".formatted(name, age);

		try (var con = getConnectionWith1String(); 
				var stmt = con.createStatement()) {

			var row = stmt.executeUpdate(sql);
			assertEquals(1, row);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
