package com.jdc.mkt.services;

import java.util.List;
import java.util.Optional;

public interface CrudOperation<T> {

	List<T> selectAll();

	Optional<T> findById(int id);

	void insert(T t);

	void update(T t);

}
