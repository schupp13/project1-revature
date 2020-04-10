package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.Reimb;

public class DirectServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Reimb test = new Reimb(1, 500, "2020-04-08", "2020-04-08", "the is sa description", "url to s3",
				"philip", "adam", "Pending", "food");
		
		
		
		resp.setContentType("application/json");
		resp.getWriter().println(test);
	}

}
