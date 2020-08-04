package com.dollarsbank.model;

/**
 * an object used to store a user's account information
 */
public class Account {
	Customer customer;
	int account_number;
	double funds;
	String history;
	SavingsAccount savingsAccount;

	public void setCustomer(Customer customer) { this.customer = customer; }
	public Customer getCustomer() { return this.customer; }

	public void setAccountNumber(int account_number) { this.account_number = account_number; }
	public double getAccountNumber() { return this.account_number; }

	public void setFunds(double funds) { this.funds = funds; }
	public double getFunds() { return this.funds; }
	
	public void setHistory(String history) { this.history = history; }
	public String getHistory() { return this.history; }

	public void setSavingsAccount(SavingsAccount savingsAccount) { this.savingsAccount = savingsAccount; }
	public SavingsAccount getSavingsAccount() { return this.savingsAccount; }

}