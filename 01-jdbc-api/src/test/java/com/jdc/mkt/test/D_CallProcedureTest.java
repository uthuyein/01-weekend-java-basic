package com.jdc.mkt.test;

import static com.jdc.mkt.factory.MySqlConnector.*;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

public class D_CallProcedureTest extends JunitFactory {

	@Test
	void procedureTest() {
		try (var con = getConnectionWithProps();
			var stmt = con.prepareCall("{call productNamelike(?)}");) {	
			stmt.setString(1, "Banana");
			var rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
