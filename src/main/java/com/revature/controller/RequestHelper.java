package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RequestHelper {
	
	
	public static void directProcess(HttpServletRequest req, HttpServletResponse resp) {
		switch(req.getRequestURI()) {
	
		case "/project-1/getUser.json":
			System.out.println("test 2");
			new UserController().getUser(req, resp);
			break;
		case "/project-1/getUsersList.json":
			System.out.println("teeldsflasdfl;djfdsfj");
			new UserController().getUsers(req,resp);
			break;
		case "/project-1/getTypes.json":
			System.out.println("test 3");
			new ReimbController().getTypes(req, resp);
			break;
		case "/project-1/createReimb.json":
			System.out.println("test 4");
			new ReimbController().insertReimb(req,resp);
			break;
		case "/project-1/getUserReimb.json":
			System.out.println("test 5");
			new ReimbController().getReimbUserId(req,resp);
			break;
		case "/project-1/getUserReimbById.json":
			System.out.println("test 5");
			new ReimbController().getReimbUserIdPost(req,resp);
			break;
		case "/project-1/getAllReimb.json":
			new ReimbController().getAll(req,resp);
			break;
		case "/project-1/approveById.json":
			System.out.println("HiTTING APPROVE ID");
			new ReimbController().updateReimb(req, resp);
			break;
		case "/project-1/denyById.json":
			System.out.println("HiTTING APPROVE ID");
			new ReimbController().updateReimb(req, resp);
			break;
		
			
		}
	}

	public static String process(HttpServletRequest req, HttpServletResponse resp) {
		switch(req.getRequestURI()) {
	case "/project-1/login.view":
		System.out.println("test 1");
		return new UserController().login(req, resp);
	case "/project-1/logout.view":
		System.out.println("test 6");
		return new UserController().logout(req, resp);
	case "/project-1/manager.view":
		System.out.println("test 9");
		return new UserController().manager(req, resp);
		
		}
		return "";
	}
}
