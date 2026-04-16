package com.jdc.mkt.listener_filter;

import static com.jdc.mkt.utils.MySqlConnector.getConnection;

import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ApplicationListener implements ServletContextListener{

	private static List<String> ddlList = List.of(
			"drop database if exists testDb",
			"create database testDb",
			"use testDb",
			"""
			create table category_tbl(
				id int primary key auto_increment,
				name varchar(45) not null unique,
				create_date date default (current_date),
				is_active tinyint(1) default 1
				);
			""",
			"""
			create table product_tbl(
				id int primary key auto_increment,
				category_id int,
				name varchar(45) not null ,
				price double not null ,
				create_date date default (current_date),
				is_active tinyint(1) default 1,
				foreign key (category_id) references category_tbl(id)
				);
			"""
			
			);
	private static List<String> initList = List.of(
			"set foreign_key_checks = 0",
			"INSERT INTO category_tbl(name) VALUES('Dry Fruits')",
			"INSERT INTO category_tbl(name) VALUES('Fresh Fruits')",
			"INSERT INTO category_tbl(name) VALUES('Hot Drinks')",
			"INSERT INTO category_tbl(name) VALUES('Cold Drinks')",
			"INSERT INTO product_tbl(name,price,category_id) VALUES('Banana',1200.00,2)",
			"INSERT INTO product_tbl(name,price,category_id) VALUES('Orange',1500.00,2)",
			"set foreign_key_checks = 1"
			);
	
	
	static void init() {
		try(var con = getConnection();
			var stmt = con.createStatement()){
			
			for(String str: ddlList) {
				stmt.addBatch(str);
			}
			for(String str:initList) {
				stmt.addBatch(str);
			}
			
			stmt.executeLargeBatch();
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		init();
	}
}
