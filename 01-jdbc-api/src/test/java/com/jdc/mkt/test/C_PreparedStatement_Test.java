package com.jdc.mkt.test;

import java.sql.SQLException;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.jdc.mkt.factory.MySqlConnector.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class C_PreparedStatement_Test extends JunitFactory{

	@Order(3)
	@ParameterizedTest
	@ValueSource(ints = {1})
	void selectTest(int id) {
		var sql = "select * from data_tbl where id = ?";
		
		try(var con = getConnectionWithProps()){
			var stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			
			var rs = stmt.executeQuery();
			
			
			while(rs.next()) {
				System.out.print("ID : "+rs.getInt(1)+"\t");
				System.out.print("Name : "+rs.getString(2)+"\t");
				System.out.println("Age : "+rs.getInt(3));
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Order(4)
	@ParameterizedTest
	@ValueSource(ints = {1})
	void deleteTest(int id) {
		var sql = "delete from data_tbl where id = ?";
		
		try(var con = getConnectionWithProps()){
			var stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			var row = stmt.executeUpdate();
			assertEquals(1, row);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Order(2)
	@ParameterizedTest
	@CsvSource(value = {"Patrick Nauthan:72:1"},delimiter = ':')
	void updateTest(String name,int age,int id) {
		var sql = "update data_tbl set name = ? ,age = ? where id = ?";
		
		try(var con = getConnectionWithProps()){
			var stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setInt(2, age);
			stmt.setInt(3, id);
			var row = stmt.executeUpdate();
			assertEquals(1, row);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Order(1)
	@ParameterizedTest
	@CsvSource(value = {"Patrick:70"},delimiter = ':')
	void insertTest(String name,int age) {
		var sql = "insert into data_tbl(name,age) values(?,?)";
		
		try(var con = getConnectionWithProps()){
			var stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setInt(2, age);
			
			var row = stmt.executeUpdate();
			assertEquals(1, row);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
