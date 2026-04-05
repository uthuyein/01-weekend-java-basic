package com.jdc.mkt.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Category {

	private int id;
	private String name;
	private LocalDate createDate;
	private boolean isActive;
}
