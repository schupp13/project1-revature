package com.revature.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.config.ConnectionUtil;
import com.revature.model.Type;

public class TypeDao implements ContractDao<Type, Integer>{

	public List<Type> findAll() {
		try(Connection conn = ConnectionUtil.connect()) {
			String sql = "select * from ers_reimbursement_type";
			Statement s = conn.createStatement();
			List<Type> list = new ArrayList<Type>();
			
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				list.add(new Type(rs.getInt(1), rs.getString(2)));
			}
			return list;
		}catch(SQLException e) {
			
		}
		return null;
	}

	public Type findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Type findByAString(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	public Type updateByID(Type t) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean insert(Type t) {
		// TODO Auto-generated method stub
		return false;
	}

}
