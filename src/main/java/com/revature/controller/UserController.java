package com.revature.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.User;
import com.revature.services.UserService;

public class UserController {
	private UserService user;
	
	public UserController() {
		 user = new UserService();
	
	}
	
	public void login(HttpServletRequest req, HttpServletResponse resp) {
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		User u =  user.login(username, password);
		// this is how to take an object and turn it into json
		if(u == null) {
			
		}else {
			HttpSession session = req.getSession();
			session.setAttribute("id", u.getId());
			session.setAttribute("username", u.getUsername());
			session.setAttribute("role", u.getRole());
			session.setAttribute("email", u.getEmail());
			session.setAttribute("firstName", u.getFirstName());
			session.setAttribute("lastName", u.getLastName());
			session.setAttribute("password", u.getPassword());
			
		
			try {
				req.getRequestDispatcher("html/user.html").forward(req, resp);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	public void getUser(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		System.out.println(session.getAttribute("id"));
		User user = new User((Integer)session.getAttribute("id"), (String)session.getAttribute("username"), (String)session.getAttribute("password"), (String)session.getAttribute("firstName"), (String)session.getAttribute("lastName"), (String)session.getAttribute("email"), (Integer)session.getAttribute("role"));
		resp.setContentType("application/json");
		try {
		resp.getWriter()
			.write(
					new ObjectMapper()
						.writeValueAsString(user));			
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	

	

}
