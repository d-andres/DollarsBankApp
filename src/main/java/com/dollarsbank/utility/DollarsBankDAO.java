package com.dollarsbank.utility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dollarsbank.model.Account;
import com.dollarsbank.model.Customer;

/**
 * An object used to access Data from a database.
 */
public class DollarsBankDAO {

	/**
	 * Returns a List of Account objects that are retrieved from the database
	 * @return the list of accounts in the database
	 */
	public List<Account> getAllAccounts() {
		Connection con = DatabaseConnectionUtil.getConnection();

		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM accounts");

			List<Account> list = new ArrayList<Account>();

			while (rs.next()) {
				Account a = new Account();
				Customer c = new Customer();
				
				c.setId(rs.getString("id"));
				c.setPassword(rs.getString("password"));
				c.setName(rs.getString("name"));
				c.setPhone(rs.getString("phone"));
				a.setCustomer(c);
				a.setAccountNumber(rs.getInt("account_number"));
				a.setFunds(rs.getDouble("funds"));
				a.setHistory(rs.getString("history"));

				list.add(a);
			}

			con.close();
			stmt.close();
			rs.close();

			return list;
		} catch(SQLException ex) {
			ex.printStackTrace();
		}

		return null;
	}
	
	/**
	 * Returns an Account object containing data from a specific account from the database
	 * @param id the user's id
	 * @param password the user's password
	 * @return the Account object
	 */
	public Account getAccount(String id, String password) {
		Connection con = DatabaseConnectionUtil.getConnection();

		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM accounts WHERE id='" + id +"' AND password='" + password + "'");

			if (rs.next()) {
				Account a = new Account();
				Customer c = new Customer();
				
				c.setId(rs.getString("id"));
				c.setPassword(rs.getString("password"));
				c.setName(rs.getString("name"));
				c.setAddress(rs.getString("address"));
				c.setPhone(rs.getString("phone"));
				a.setCustomer(c);
				a.setAccountNumber(rs.getInt("account_number"));
				a.setFunds(rs.getDouble("funds"));
				a.setHistory(rs.getString("history"));

				con.close();
				stmt.close();
				rs.close();
				
				return a;
			}
			con.close();
			stmt.close();
			rs.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * A method used to update the table in the database with a user's account
	 * @param account the Account object containing the data to be updated to the database
	 */
	public void addAccount(Account account) {
		
		Connection con = DatabaseConnectionUtil.getConnection();
		try{
			Statement stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO `dollarsbank`.`accounts` (`id`, `password`, `name`, 'address', `phone`, `funds`, 'history') " 
				+ "VALUES ('" + account.getCustomer().getId() + "', '" + account.getCustomer().getPassword() + "', '" + account.getCustomer().getName() 
				+ "', '" + account.getCustomer().getPhone() + "', '" + account.getFunds() + "', '" + account.getHistory() + "');");
			con.close();
			stmt.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Used to update an account's balance and transaction history
	 * @param account the Account object containing the data to be updated to the database
	 */
	public void updateAccount(Account account) {
		Connection con = DatabaseConnectionUtil.getConnection();
		try{
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE `dollarsbank`.`accounts` SET `funds` = '" + account.getFunds() + "', `history` = '" + account.getHistory() 
				+ "' WHERE (`account_number` = '" + account.getAccountNumber() + "');");
			con.close();
			stmt.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
}