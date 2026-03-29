package com.jdc.mkt.test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.mkt.entity.Category;
import com.jdc.mkt.entity.Product;

import static com.jdc.mkt.utils.MySqlConnector.*;

import java.util.ArrayList;
import java.util.List;
public class ProductTest extends JunitFactory{

	@ParameterizedTest
	@CsvSource({
		"Fresh Fruits,,,",
		",Banana,,",
		",,1000,2000",
		"Fresh Fruits,Banana,,"})
	void dynmaicSearch(String category,String product,Double pFrom ,Double pTo) {
	
		List<Object> temp = new ArrayList<>();
		List<Product> products = new ArrayList<Product>();
		
		var sb =getQueryString(temp, category, product, pFrom, pTo);
		System.out.println(sb.toString());
		try(var con = getConnection();
			var stmt = con.prepareStatement(sb.toString())){
			
			for(int i = 0 ;i < temp.size();i++) {
				stmt.setObject(i+1, temp.get(i));
			}
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				var p = new Product();
				var c = new Category();
				c.setId(rs.getInt("c.id"));
				c.setName(rs.getString("c.name"));
				p.setId(rs.getInt("p.id"));
				p.setName(rs.getString("p.name"));
				p.setPrice(rs.getDouble("p.price"));
				p.setCategory(c);
				products.add(p);
			}
			
			products.forEach(p -> System.out.println(p.getName()));
			System.out.println("=========== Finish =============");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private StringBuilder getQueryString(List<Object> temp ,String category,String product,Double pFrom ,Double pTo) {
		StringBuilder sb = new StringBuilder("""
				select c.id,p.id,c.name,p.name,p.price from product_tbl p 
				join category_tbl c on p.category_id = c.id
				where 1=1 
				""");
		
		if(null != category) {
			sb.append(" and c.name = ?");
			temp.add(category);
		}
		
		if(null != product) {
			sb.append(" and p.name like lower(?)");
		 	temp.add(product.toLowerCase().concat("%"));
		}
		
		if(null != pFrom && pFrom > 0 ) {
			sb.append(" and p.price >= ?");
			temp.add(pFrom);
		}
		
		if(null != pTo && pTo > 0 ) {
			sb.append(" and p.price <= ?");
			temp.add(pTo);
		}
		
		
		return sb;
	}
}
