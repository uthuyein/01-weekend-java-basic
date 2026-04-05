package com.jdc.mkt.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Product {

	private int id;
	private String name;
	private double price;
	private Category category;
	private LocalDate createDate;
	
	private boolean isActive;
}
