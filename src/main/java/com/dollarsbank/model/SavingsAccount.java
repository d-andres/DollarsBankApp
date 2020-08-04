package com.dollarsbank.model;

/**
 * an object to store data for a customer's savings account
 */
public class SavingsAccount {
	private int account_number;
	private Customer customer;
	private double funds;

	public void setAccountNumber(int account_number) { this.account_number = account_number; }
	public double getAccountNumber() { return this.account_number; }

	public void setCustomer(Customer customer) { this.customer = customer; }
	public Customer getCustomer() { return this.customer; }

	public void setFunds(double funds) { this.funds = funds; }
	public double getFunds() { return this.funds; }
}