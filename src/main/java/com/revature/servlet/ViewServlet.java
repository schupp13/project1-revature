package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controller.RequestHelper;
import com.revature.model.Reimb;


@WebServlet(urlPatterns = {"*.view"}, loadOnStartup = 2)	
public class ViewServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("hitting view servlet: GET");
	req.getRequestDispatcher(RequestHelper.process(req, resp)).forward(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("hitting view servlet: Post");
		req.getRequestDispatcher(RequestHelper.process(req, resp)).forward(req,resp);
	}

}
