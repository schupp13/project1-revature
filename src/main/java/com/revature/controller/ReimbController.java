package com.revature.controller;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Reimb;
import com.revature.model.Type;

import com.revature.services.ReimbService;
import com.revature.services.TypeService;

public class ReimbController {
	private TypeService ts;
	private ReimbService rb;
	
	public static final Logger logger = Logger.getLogger("ERS REIMB CONTROLLER: ");
	
	public ReimbController() {
			ts = new TypeService();
			rb = new ReimbService();
	}
	
	public void getTypes(HttpServletRequest req, HttpServletResponse resp){
		List<Type> list = new ArrayList<Type>();
		list = ts.findAll();
		logger.info("Getting all types for the form");
		
		try {
			resp.getWriter()
				.write(
						new ObjectMapper()
							.writeValueAsString(list));			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("JSON PROCESSING EXCEPTION IN getTypes: might want to check dependecy");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
}

	public void insertReimb(HttpServletRequest req, HttpServletResponse resp) {
		int authorId = Integer.parseInt(req.getParameter("author"));
		Float amount = Float.parseFloat(req.getParameter("amount"));
		String description = req.getParameter("description");
		String reciept = req.getParameter("reciept");
		int typeId = Integer.parseInt(req.getParameter("typeId"));
		
		System.out.println(amount);
		System.out.println(description);
		System.out.println(reciept);
		System.out.println(authorId);
		System.out.println(typeId);

		Reimb reimb =  new Reimb(authorId, typeId, amount, description);
		logger.info("Inserting reimb");
		
			boolean insert = rb.insertReimb(reimb);
			if(insert == true) {
				try {
					req.getRequestDispatcher("html/user.html").forward(req, resp);
				} catch (ServletException | IOException e) {
				
					e.printStackTrace();
				}
			}
			
	}

	public void getReimbUserId(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		int id = (int) session.getAttribute("id");
		List<Reimb> list = new ArrayList<Reimb>();
		logger.info("Getting reimb by id and printing out json");
		list = rb.findReimbByAuthorId(id);
		try {
			resp.getWriter()
				.write(
						new ObjectMapper()
							.writeValueAsString(list));			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void getReimbByType(HttpServletRequest req, HttpServletResponse resp) {
		
		
		System.out.println(req.getAttribute("type") + "inside getReimbByType");
		List<Reimb> list = new ArrayList<Reimb>();
		
		//list = rb.getAllReimbPending();
	}

	public void getAll(HttpServletRequest req, HttpServletResponse resp) {
		
		System.out.println(req.getAttribute("type") + "inside getReimbByType");
		List<Reimb> list = new ArrayList<Reimb>();
		
		list = rb.getAllReimb();
		try {
			resp.getWriter()
				.write(
						new ObjectMapper()
							.writeValueAsString(list));			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void updateReimb(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		int status = Integer.parseInt(req.getParameter("status"));
		HttpSession session = req.getSession();
		int resolver = (int) session.getAttribute("id");
		
		
		System.out.println(id);
		System.out.println(status);
	
		boolean update = rb.updateStatus(id, status, resolver);
		
			if(update == true) {
				try {
					req.getRequestDispatcher("html/manager.html").forward(req, resp);
				} catch (ServletException | IOException e) {
				
					e.printStackTrace();
				}
			}
			
		
	}

	public void getReimbUserIdPost(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("userId"));
		System.out.println(id);
		List<Reimb> list = new ArrayList<Reimb>();
		logger.info("Getting reimb by id and printing out json");
		list = rb.findReimbByAuthorId(id);
		try {
			resp.getWriter()
				.write(
						new ObjectMapper()
							.writeValueAsString(list));			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		 
	}

	
}
	
