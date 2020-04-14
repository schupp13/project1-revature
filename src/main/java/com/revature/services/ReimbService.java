package com.revature.services;

import java.util.List;


import com.revature.model.Reimb;
import com.revature.dao.ReimbDao;
import java.util.ArrayList;
public class ReimbService {
	
	private ReimbDao rd;
	
	public ReimbService(){
		super();
		ReimbDao rd = new ReimbDao();
	}
	
	
	public List<Reimb> getAllCustomers(){	
		return  rd.findAll();
	}
	
	public Reimb getById(int id) {
		return rd.findById(id);
	}


	public boolean insertReimb(Reimb reimb) {
		return rd.insert(reimb);
		
	}
	
	
	
}
