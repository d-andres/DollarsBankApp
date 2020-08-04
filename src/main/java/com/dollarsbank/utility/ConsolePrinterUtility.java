package com.dollarsbank.utility;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dollarsbank.model.Account;
import com.dollarsbank.model.Customer;

/**
 * An object used to print out the text in the console for the user menus. 
 */
public class ConsolePrinterUtility {

	ColorsUtility color = new ColorsUtility();

	/**
	 * Prints out the Welcome menu for the application.
	 */
	public void printWelcome() {
		System.out.println(color.BLUE + "+---------------------------+\n" + "| DOLLARSBANK Welcomes You! |\n" + "+---------------------------+" + color.RESET);
		System.out.println("1. Create New Account\n" + "2. Login\n" + "3. Exit.\n");
		System.out.println(color.GREEN + "Enter Choice (1,2 or 3) :" + color.RESET);
	}

	/**
	 * Prints out the menu for the user to create a new account.
	 * @param sysin the Scanner object for user input
	 * @return the Account object with the provided data
	 */
	public Account printNewAccount(Scanner sysin) {
		Customer customer = new Customer();
		Account account = new Account();
		System.out.println(color.BLUE + "+-------------------------------+\n" + "| Enter Details For New Account |\n" + "+-------------------------------+" + color.RESET);
		System.out.println("Customer Name:" + color.GREEN);
		String input = sysin.nextLine();
		customer.setName(input);
		input = "";

		System.out.println(color.RESET + "Customer Address:" + color.GREEN);
		input = sysin.nextLine();
		customer.setAddress(input);

		System.out.println(color.RESET + "Customer Contact Number:" + color.GREEN);
		input = sysin.nextLine();
		customer.setPhone(input);

		System.out.println(color.RESET + "Customer Id :" + color.GREEN);
		input = sysin.nextLine();
		customer.setId(input);

		while(true)
		{
			System.out.println(color.RESET + "Password : 8 Characters With Lower, Upper, Number & Special" + color.GREEN);
			input = sysin.nextLine();
			String regex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(input);
			if(matcher.matches()) {
				customer.setPassword(input);
				break;
			} 
			else
				printError(3);
			
		}
		
		System.out.println(color.RESET + "Initial Deposit Amount:" + color.GREEN);
		input = sysin.nextLine();
		account.setFunds(Double.parseDouble(input));
		String history = "+ $" + Double.parseDouble(input) + " on " + java.util.Calendar.getInstance().getTime() + "%";
		account.setHistory(history);
		
		account.setCustomer(customer);

		return account;
	}

	/**
	 * Prints out the Login menu
	 * @param sysin the Scanner object for user input
	 * @param dao the data access object to retrieve info from the database
	 * @return the Account object with a username and password
	 */
	public Account printLogin(Scanner sysin, DollarsBankDAO dao) {
		System.out.println(color.BLUE + "+---------------------+\n" + "| Enter Login Details |\n" + "+---------------------+" + color.RESET);
		System.out.println("User Id :" + color.GREEN);
		String id = sysin.nextLine();
		System.out.println(color.RESET + "Password :" + color.GREEN);
		String password = sysin.nextLine();

		Account account = dao.getAccount(id, password);
		return account;
	}

	/**
	 * Prints out the menu that contains the customer options.
	 */
	public void printCustomerMenu() {
		System.out.println(color.BLUE + "+---------------------+\n" + "| WELCOME Customer!!! |\n" + "+---------------------+" + color.BLUE);
		System.out.println("1. Deposit Amount\n" + "2. Withdraw Amount\n" + "3. Funds Transfer\n" + "4. View 5 Recent Transactions\n" + "5. Display Customer Information\n" + "6. Sign Out\n");
		System.out.println(color.GREEN + "Enter Choice (1,2,3,4,5 or 6) :" + color.RESET);
	}

	/**
	 * Prints the Deposit menu
	 * @param sysin the Scanner object for user input
	 * @return the double of the amount to deposit
	 */
	public void printDepositMenu(Scanner sysin, Account account) {
		System.out.println(color.BLUE + "+---------+\n" + "| Deposit |\n" + "+---------+" + color.RESET);
		System.out.println("Enter amount: " + color.GREEN);
		String input = sysin.nextLine();
		account.setFunds(account.getFunds() + Double.parseDouble(input));
		String history = "+ $" + Double.parseDouble(input) + " on " + java.util.Calendar.getInstance().getTime() + "%";
		account.setHistory(account.getHistory() + history);
	}

	/**
	 * Prints the Withdraw menu
	 * @param sysin the Scanner object for user input
	 * @return the double of the amount to withdraw
	 */
	public void printWithdrawMenu(Scanner sysin, Account account) {
		System.out.println(color.BLUE + "+----------+\n" + "| Withdraw |\n" + "+----------+" + color.RESET);
		System.out.println("Enter amount: " + color.GREEN);
		String input = sysin.nextLine();
		double newbalance = account.getFunds() - Double.parseDouble(input);
		if (newbalance < 0)
			System.out.println("Insufficient funds.");
		else {
			account.setFunds(newbalance);
			String history = "- $" + input + " on " + java.util.Calendar.getInstance().getTime() + "%";
			account.setHistory(account.getHistory() + history);
		}
		
	}

	/**
	 * Prints the Funds tranfer menu. Allows the user to tranfer to or from the savings account.
	 * @param sysin the Scanner object for user input
	 * @param account the account containing the data of the funds
	 */
	public void printFundsTransfer(Scanner sysin, Account account) {
		System.out.println(color.BLUE + "+----------------+\n" + "| Funds Transfer |\n" + "+----------------+" + color.RESET);
		System.out.println("1. Transfer to Savings account.\n" + "2. Transfer from Savings account.");
		System.out.println(color.GREEN + "Enter Choice (1 or 2) :" + color.RESET);
		String input = sysin.nextLine();
		switch(Integer.parseInt(input)) {
			case 1:
				System.out.println("Enter amount to transfer: " + color.GREEN);
				input = sysin.nextLine();
				double transfer1 = Double.parseDouble(input);
				double newbalance1 = account.getFunds() - transfer1;
				if (newbalance1 < 0)
					System.out.println("Insufficient funds in account.");
				else {
					account.setFunds(newbalance1);
					account.getSavingsAccount().setFunds(account.getSavingsAccount().getFunds() + transfer1);
					String history = "- $" + input + " on " + java.util.Calendar.getInstance().getTime() + "%";
					account.setHistory(account.getHistory() + history);
				}
				break;
			case 2:
				System.out.println("Enter amount to transfer: " + color.GREEN);
				input = sysin.nextLine();
				double transfer2 = Double.parseDouble(input);
				double newbalance2 = account.getSavingsAccount().getFunds() - transfer2;
				if (newbalance2 < 0)
					System.out.println("Insufficient funds Savings account.");
				else {
					account.getSavingsAccount().setFunds(newbalance2);
					account.setFunds(account.getFunds() + transfer2);
					String history = "+ $" + input + " on " + java.util.Calendar.getInstance().getTime() + "%";
					account.setHistory(account.getHistory() + history);
				}
				break;
			default:
				printError();
		}
		
	}

	/**
	 * Print the customer's 5 most recent transactions
	 * @param account the user's account
	 */
	public void printRecentTransactions(Account account) {
		System.out.println(color.BLUE + "+------------------------+\n" + "| 5 Recent Transactions: |\n" + "+------------------------+" + color.RESET);
		String history[] = account.getHistory().split("%");
		if (history.length > 5)
			for(int i = history.length-1; i > history.length-6; i-- )
				System.out.println(history[i]);
		else 
			for(int i = history.length-1; i >= 0; i--)
				System.out.println(history[i]);
		System.out.println("-----------------------------------------------\n" + "Balance : $" + account.getFunds());
	}

	/**
	 * Prints the customer's information
	 * @param customer the user's customer information
	 */
	public void printCustomerInfo(Customer customer) {
		System.out.println(color.BLUE + "+----------------------+\n" + "| Customer Information |\n" + "+----------------------+" + color.RESET);
		System.out.println("Customer Name: " + color.GREEN + customer.getName());
		System.out.println(color.RESET + "Customer Address: " + color.GREEN + customer.getAddress());
		System.out.println(color.RESET + "Customer Contact Number: " + color.GREEN + customer.getPhone());
	}

	/**
	 * Prints the error message for invalid input
	 */
	public void printError() {
		System.out.println(color.RED + "Invalid Input. Try Again!" + color.RESET);
	}

	/**
	 * Prints the error message for invalid input
	 * @param error the number to specify the error that is needed
	 */
	public void printError(int error) {
		switch(error) {
			case 1:
				System.out.println(color.RED + "Invalid Selection. Try Again!" + color.RESET);
				break;
			case 2:
				System.out.println(color.RED + "Invalid Credentials. Try Again!" + color.RESET);
				break;
			case 3:
				System.out.println(color.RED + "Invalid Combination. Try Again!" + color.RESET);
				break;
			default:
				System.out.println(color.RED + "Invalid Input. Try Again!" + color.RESET);
		}
	}
}