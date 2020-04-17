package com.revature.services;

import java.util.List;


import com.revature.model.Reimb;
import com.revature.dao.ReimbDao;
import java.util.ArrayList;
public class ReimbService {
	
	private ReimbDao rd;
	
	public ReimbService(){
		super();
		 rd = new ReimbDao();
	}
	
	/**
	 * 
	 * @param id 
	 * @return
	 */
	public List<Reimb> findReimbByAuthorId(int id){	
		return  rd.findAllByAuthorId(id);
	}
	
	public Reimb getById(int id) {
		return rd.findById(id);
	}


	public boolean insertReimb(Reimb reimb) {
		return rd.insert(reimb);
		
	}

	public List<Reimb> getAllReimb() {
		return rd.findAll();
	}

	public boolean updateStatus(int id, int status, int resolver) {
		
		return rd.updateStatus(id, status, resolver);
	}


	
	
	
}
