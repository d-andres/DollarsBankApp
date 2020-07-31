package com.dollarsbank.model;

public class Customer {
	private String name;
	private String address;
	private String phone;
	private String id;
	private String password;

	public void setName(String name) { this.name = name; }
	public String getName() { return this.name; }

	public void setAddress(String address) { this.address = address; }
	public String getAddress() { return this.address; }

	public void setPhone(String phone) { this.phone = phone; }
	public String getPhone() { return this.phone; }

	public void setId(String id) { this.id = id; }
	public String getId() { return this.id; }

	public void setPassword(String password) { this.password = password; }
	public String getPassword() { return this.password; }

}