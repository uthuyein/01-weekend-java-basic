package com.jdc.mkt.test;

import static com.jdc.mkt.utils.MySqlConnector.getConnection;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
public class ProductInsertWithTransaction extends JunitFactory{

	@ParameterizedTest
	@CsvSource("Lemon,2,20")
	void insertTest(String name,int id,double price) throws SQLException {
		Connection con = null;
		String sql = "insert into product_tbl(name,category_id,price)values(?,?,?)";
		
		try{
			con = getConnection();
			var stmt = con.prepareStatement(sql);
			con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
			con.setAutoCommit(false);	
			stmt.setString(1, name);
			stmt.setInt(2, id);
			stmt.setDouble(3, price);
			
			if(price < 100) {
				throw new RuntimeException("Price doesn't allowed");
			}
			stmt.executeUpdate();
			con.commit();
			
			}catch (Exception e) {
				con.rollback();
				e.printStackTrace();
			}finally {
				con.close();
			}
	}
}
