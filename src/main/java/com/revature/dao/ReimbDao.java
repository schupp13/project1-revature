package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.revature.model.Reimb;
import com.revature.config.ConnectionUtil;

public class ReimbDao implements ContractDao<Reimb, Integer> {

	public List<Reimb> findAll() {
		try(Connection conn = ConnectionUtil.connect()){
			String sql = "select er.reimb_id, er.reimb_author, er.reimb_resolver, er.reimb_type_id, er.reimb_amount, er.reimb_submited, er.reimb_resolved ,  \r\n" + 
					"reimb_description , reimb_receipt , \r\n" + 
					"concat(author.user_first_name, ' ',  author.user_last_name) as authorName , \r\n" + 
					"concat(resolver.user_first_name, ' ', resolver.user_last_name) as resolverFullName, erss.reimb_status, erst.reimb_type \r\n" + 
					"from ers_reimbursement er \r\n" + 
					"left join ers_reimbursement_status erss on erss.reimb_status_id = er.reimb_status_id\r\n" + 
					"left join ers_reimbursement_type erst on erst.reimb_type_id  = er.reimb_type_id\r\n" + 
					"left join ers_user author on author.ers_user_id = er.reimb_author\r\n" + 
					"left join ers_user resolver on resolver.ers_user_id = er.reimb_resolver order by er.reimb_submited desc";
			Statement stmt = conn.createStatement();
			List<Reimb> gs = new ArrayList<>();
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				gs.add(new Reimb(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getFloat(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getString(10),rs.getString(11),rs.getString(12), rs.getString(13)));
			}
			
			rs.close();
			return gs;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Reimb> findAllByAuthorId(int id) {
		try(Connection conn = ConnectionUtil.connect()){
			String sql = "select er.reimb_id, er.reimb_author, er.reimb_resolver, er.reimb_type_id, er.reimb_amount, er.reimb_submited, er.reimb_resolved ,  \r\n" + 
					"reimb_description , reimb_receipt , \r\n" + 
					"concat(author.user_first_name, ' ',  author.user_last_name) as authorName , \r\n" + 
					"concat(resolver.user_first_name, ' ', resolver.user_last_name) as resolverFullName, erss.reimb_status, erst.reimb_type \r\n" + 
					"from ers_reimbursement er \r\n" + 
					"left join ers_reimbursement_status erss on erss.reimb_status_id = er.reimb_status_id\r\n" + 
					"left join ers_reimbursement_type erst on erst.reimb_type_id  = er.reimb_type_id\r\n" + 
					"left join ers_user author on author.ers_user_id = er.reimb_author\r\n" + 
					"left join ers_user resolver on resolver.ers_user_id = er.reimb_resolver where er.reimb_author = ? order by er.reimb_submited desc";
			PreparedStatement ps = conn.prepareStatement(sql);
			List<Reimb> gs = new ArrayList<>();
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				gs.add(new Reimb(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getFloat(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getString(10),rs.getString(11),rs.getString(12), rs.getString(13)));
			}
			ps.close();
			rs.close();
			return gs;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public Reimb findById(Integer id) {
		try(Connection conn = ConnectionUtil.connect()){
		String sql = "select * from ers_reimbursement where ers_reimbursement.reimb_id = ?;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1,id);
		ResultSet rs = ps.executeQuery(sql);
		rs.next();
		return new Reimb(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getFloat(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
				rs.getString(10),rs.getString(11),rs.getString(12), rs.getString(13));
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	
	
	public Reimb findByAString(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	public Reimb updateByID(Reimb t) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean insert(Reimb t) {
		try(Connection conn =  ConnectionUtil.connect()){
		String sql = "insert into ers_reimbursement (reimb_amount, reimb_description ,reimb_author, reimb_type_id ) values (?, ?, ?, ?) returning reimb_id";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setFloat(1,t.getAmount());
		ps.setString(2,t.getDescription());
		ps.setInt(3, t.getAuthorId());
		ps.setInt(4, t.getTypeId());
		 
		ResultSet rs = ps.executeQuery();
		
		ps.close();
	
			if(rs != null) {
				return true;
			}
		
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateStatus(int id, int status, int resolver) {
		try(Connection conn =  ConnectionUtil.connect()){
		String sql = "update ers_reimbursement set reimb_resolved = NOW(),  reimb_resolver = ?, reimb_status_id = ? where reimb_id = ? returning true";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setInt(1, resolver);
		ps.setInt(2, status);
		ps.setInt(3, id);
		ResultSet rs = ps.executeQuery();
		
		ps.close();
	
			if(rs != null) {
				return true;
			}
		
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
		}
	

}
