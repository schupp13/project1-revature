package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RequestHelper {
	
	
	public static void directProcess(HttpServletRequest req, HttpServletResponse resp) {
		switch(req.getRequestURI()) {
		case "/project-1/login.json":
			new UserController().login(req, resp);
			break;
		case "/project-1/getUser.json":
			new UserController().getUser(req, resp);
			break;
		case "/project-1/getTypes.json":
			new ReimbController().getTypes(req, resp);
			break;
		case "/project-1/createReimb.json":
			new ReimbController().insertReimb(req,resp);
		}
	}
}
