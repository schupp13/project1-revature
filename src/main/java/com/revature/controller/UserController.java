package com.revature.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Type;
import com.revature.model.User;
import com.revature.services.UserService;

public class UserController {
	private UserService user;
	
	public UserController() {
		 user = new UserService();
	
	}
	
	/**
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	public String login(HttpServletRequest req, HttpServletResponse resp) {
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		User u =  user.login(username, password);
		if(u != null) {
			HttpSession session = req.getSession();
			session.setAttribute("id", u.getId());
			session.setAttribute("username", u.getUsername());
			session.setAttribute("role", u.getRole());
			session.setAttribute("email", u.getEmail());
			session.setAttribute("firstName", u.getFirstName());
			session.setAttribute("lastName", u.getLastName());
			session.setAttribute("password", u.getPassword());
		}
		if(u != null && u.getRole() == 1){
			return "html/user.html";
		}else if(u != null && u.getRole() == 2) {
			return "html/manager.html";
		}
		return "html/landing.html";
	}
	
	public void getUser(HttpServletRequest req, HttpServletResponse resp) {
		
			HttpSession session = req.getSession();
			System.out.println(session.getAttribute("id"));
			User user = new User((Integer)session.getAttribute("id"), (String)session.getAttribute("username"), (String)session.getAttribute("password"), (String)session.getAttribute("firstName"), (String)session.getAttribute("lastName"), (String)session.getAttribute("email"), (Integer)session.getAttribute("role"));
			resp.setContentType("application/json");
			try {
			resp.getWriter().write(new ObjectMapper().writeValueAsString(user));			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}

	public String logout(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("INSIDE LOGOUT");
		HttpSession session = req.getSession();
		 session.invalidate();
		return "html/landing.html";
	}

	public String manager(HttpServletRequest req, HttpServletResponse resp) {
		return "html/manager.html";
	}

	public void getUsers(HttpServletRequest req, HttpServletResponse resp){
		List<User> list = new ArrayList<User>();
		list = user.getAll();
		//logger.info("Getting all types for the form");
		
		try {
			resp.getWriter()
				.write(
						new ObjectMapper()
							.writeValueAsString(list));			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//logger.error("JSON PROCESSING EXCEPTION IN getTypes: might want to check dependecy");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
}
	

	

}
