package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.config.ConnectionUtil;
import com.revature.model.Type;
import com.revature.model.User;

public class UserDao implements ContractDao<User, Integer> {
	


	public List<User> findAll() {
		try(Connection conn = ConnectionUtil.connect()) {
			String sql = "select * from ers_user order by ers_user_id desc";
			Statement s = conn.createStatement();
			List<User> list = new ArrayList<User>();
			
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				list.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7)));
			}
			return list;
		}catch(SQLException e) {
			
		}
		return null;
	}

	public User findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public User findByAString(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	public User updateByID(User t) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean insert(User t) {
		// TODO Auto-generated method stub
		return false;
	}

	public User login(String username, String password) {
		try(Connection conn = ConnectionUtil.connect()){
			String sql = "{ call login(?,?)}";
			CallableStatement cs = conn.prepareCall(sql);
			 
			cs.setString(1, username);
			cs.setString(2, password);
			ResultSet rs = cs.executeQuery();
			if(rs.next()) {
			User u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
			cs.close();
			return u;
			}
		}catch(SQLException e) {
			System.out.println("LOGIN ");
			e.printStackTrace();
		}
		return null;
	}
	



}
