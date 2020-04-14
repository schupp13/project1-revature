package com.revature.controller;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Reimb;
import com.revature.model.Type;
import com.revature.model.User;
import com.revature.services.ReimbService;
import com.revature.services.TypeService;

public class ReimbController {
	private TypeService ts;
	private ReimbService rb;
	
	public ReimbController() {
			ts = new TypeService();
			rb = new ReimbService();
	}
	
	public void getTypes(HttpServletRequest req, HttpServletResponse resp){
		List<Type> list = new ArrayList<Type>();
		list = ts.findAll();
		
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
		
		
			boolean insert = rb.insertReimb(reimb);
			if(insert == true) {
				try {
					req.getRequestDispatcher("html/user.html").forward(req, resp);
				} catch (ServletException | IOException e) {
				
					e.printStackTrace();
				}
			}
			
	}
}
	
