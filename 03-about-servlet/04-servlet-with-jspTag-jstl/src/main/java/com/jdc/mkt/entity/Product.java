package com.jdc.mkt.entity;

import lombok.Data;

@Data
public class Product {

	private int id;
	private String name;
	private double price;
	private Category category;
	private boolean isActive;
}
