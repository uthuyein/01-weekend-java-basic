package com.jdc.mkt.test;

import static com.jdc.mkt.factory.MySqlConnector.getConnectionWith1String;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class B_Statement_Test extends JunitFactory{
	
	
	@Test
	@Order(5)
	void batchTest() {

		var sql1 = "insert into data_tbl(name,age) values('Peter Pan',12)";
		var sql2 = "insert into data_tbl(name,age) values('Mitty',10)";
		var sql3 = "insert into data_tbl(name,age) values('Arnel',42)";

		try (var con = getConnectionWith1String(); 
				var stmt = con.createStatement()) {

			stmt.addBatch(sql1);
			stmt.addBatch(sql2);
			stmt.addBatch(sql3);
			
			stmt.executeBatch();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(4)
	void selectTest() {

		var sql = "select * from data_tbl";

		try (var con = getConnectionWith1String(); 
				var stmt = con.createStatement()) {

			var rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				System.out.print("ID : "+rs.getInt(1)+"\t");
				System.out.print("Name : "+rs.getString(2)+"\t");
				System.out.println("Age : "+rs.getInt(3));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Order(1)
	@ParameterizedTest
	@CsvSource({ "John,12", "Michael Bannett,65","Eminem,40" })
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
	
	
	@Order(2)
	@ParameterizedTest
	@CsvSource({ "John Farnham,62,1", "Michael Bannett,70,2" })
	void updateTest(String name, int age,int id) {

		var sql = "update data_tbl set name = '%s',age = %d where id= %d".formatted(name, age,id);

		try (var con = getConnectionWith1String(); 
				var stmt = con.createStatement()) {

			var row = stmt.executeUpdate(sql);
			assertEquals(1, row);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Order(3)
	@ParameterizedTest
	@CsvSource({ "1", "2" })
	void deleteTest(int id) {

		var sql = "delete from data_tbl where id= %d".formatted(id);

		try (var con = getConnectionWith1String(); 
				var stmt = con.createStatement()) {

			var row = stmt.executeUpdate(sql);
			assertEquals(1, row);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
