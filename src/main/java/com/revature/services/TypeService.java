package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.dao.TypeDao;
import com.revature.model.Type;

public class TypeService {
	private TypeDao td;
	public TypeService(){
		 td = new TypeDao();
	}
	
public List<Type> findAll(){
		List<Type> list = new ArrayList<Type>();
		list = td.findAll();
		return list;
		
	}
}
