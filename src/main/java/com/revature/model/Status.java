package com.revature.model;

public class Status {

	private int id;
	private String status;
	
	
	
	public Status(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}
	
	
	public Status() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	@Override
	public String toString() {
		return "Status [id=" + id + ", status=" + status + "]";
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
