package com.dollarsbank.utility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dollarsbank.model.Account;
import com.dollarsbank.model.Customer;

public class DollarsBankDAO {

	/**
	 * 
	 * @return
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
	 * 
	 * @param id
	 * @param password
	 * @return
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
				c.setPhone(rs.getString("phone"));
				a.setCustomer(c);
				a.setAccountNumber(rs.getInt("account_number"));
				a.setFunds(rs.getDouble("funds"));

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
}