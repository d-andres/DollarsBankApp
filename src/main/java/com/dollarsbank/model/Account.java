package com.dollarsbank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String address;
	private String phone;
	private String email;
	private String password;
	double balance;
	String history;

	public Account() {  }

	public Account (Long id, String name, String address, String phone, String email, String password, double balance, String history) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.balance = balance;
		this.history = history;
	}

	public Account (String name, String address, String phone, String email, String password) {
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.balance = 0.0;
		this.history = "";
	}

	public void setId(Long id) { this.id = id; }
	public Long getId() { return this.id; }

	public void setName(String name) { this.name = name; }
	public String getName() { return this.name; }

	public void setAddress(String address) { this.address = address; }
	public String getAddress() { return this.address; }

	public void setPhone(String phone) { this.phone = phone; }
	public String getPhone() { return this.phone; }

	public void setEmail(String email) { this.email = email; }
	public String getEmail() { return this.email; }

	public void setPassword(String password) { this.password = password; }
	public String getPassword() { return this.password; }

	public void setBalance(double balance) { this.balance = balance; }
	public double getBalance() { return this.balance; }

	public void setHistory(String history) { this.history = history; }
	public String getHistory() { return this.history; }

	@Override
	public String toString() {
		return "Account{" +
			"id=" + id +
			", name='" + name + '\'' +
			", address='" + address + '\'' +
			", phone='" + phone + '\'' +
			", password='" + password + '\'' +
			", balance=" + balance +
			", history='" + history + '\'' +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Account account = (Account) o;

		return id != null ? id.equals(account.id) : account.id == null;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

}