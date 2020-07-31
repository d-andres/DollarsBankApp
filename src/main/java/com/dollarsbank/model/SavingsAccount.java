package com.dollarsbank.model;

public class SavingsAccount {
	private Customer customer;
	private double funds;

	public void setCustomer(Customer customer) { this.customer = customer; }
	public Customer getCustomer() { return this.customer; }

	public void setFunds(double funds) { this.funds = funds; }
	public double getFunds() { return this.funds; }
}