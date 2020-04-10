package com.revature.model;

public class Reimb {
	private int id;
	private float amount;
	private String submitted;
	private String resolved;
	private String description;
	private String receipt;
	private String author;
	private String resolver;
	private String status;
	private String type;
	
	
	public Reimb(int id, float amount, String submitted, String resolved, String description, String receipt,
			String author, String resolver, String status, String type) {
		super();
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.receipt = receipt;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}
	public Reimb() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "{\"id\": " + id + ", \"amount\": " + amount + ", \"submitted\": \"" + submitted + "\", \"resolved\": \"" + resolved
				+ "\", \"description\":\"" + description + "\", \"receipt\": \"" + receipt + "\", \"author\": \"" + author + "\", \"resolver\":\""
				+ resolver + "\", \"status\": \"" + status + "\", \"type\": \"" + type + "\"}";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getSubmitted() {
		return submitted;
	}
	public void setSubmitted(String submitted) {
		this.submitted = submitted;
	}
	public String getResolved() {
		return resolved;
	}
	public void setResolved(String resolved) {
		this.resolved = resolved;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReceipt() {
		return receipt;
	}
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getResolver() {
		return resolver;
	}
	public void setResolver(String resolver) {
		this.resolver = resolver;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
