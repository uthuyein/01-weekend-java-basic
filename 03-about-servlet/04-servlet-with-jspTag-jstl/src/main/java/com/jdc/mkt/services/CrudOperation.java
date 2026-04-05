package com.jdc.mkt.services;

import java.util.List;

public interface CrudOperation<T> {

	List<T> selectAll();
	void insert(T t);
	void update(T t);
	
}
