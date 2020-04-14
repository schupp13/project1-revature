package com.revature.dao;

import java.util.List;

	public interface ContractDao<T, I> {

		List<T> findAll();
		T findById(I id);
		T findByAString(String s);
		T updateByID(T t);
		boolean insert(T t);
	
	};
