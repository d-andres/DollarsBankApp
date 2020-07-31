package com.dollarsbank.utility;

import java.util.Scanner;

import com.dollarsbank.model.Account;
import com.dollarsbank.model.Customer;

public class ConsolePrinterUtility {

	ColorsUtility color = new ColorsUtility();

	public void printWelcome() {
		System.out.println(color.BLUE + "+---------------------------+\n" + "| DOLLARSBANK Welcomes You! |\n" + "+---------------------------+" + color.RESET);
		System.out.println("1. Create New Account\n" + "2. Login\n" + "3. Exit.\n");
		System.out.println(color.GREEN + "Enter Choice (1,2 or 3) :" + color.RESET);
	}

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

		while(true) //loop for regex //TODO (?=.*[a-z])(?=.*[A-Z])(?=.*?[#?!@$%^&*-])[A-Za-z#?!@$%^&*-]{8,}$
		{
			System.out.println(color.RESET + "Password : 8 Characters With Lower, Upper & Special" + color.GREEN);
			input = sysin.nextLine();
			customer.setPassword(input);
			break;
		}
		
		System.out.println(color.RESET + "Initial Deposit Amount:" + color.GREEN);
		input = sysin.nextLine();
		account.setFunds(Double.parseDouble(input));

		account.setCustomer(customer);

		return account;
	}

	public Account printLogin(Scanner sysin, DollarsBankDAO dao) {
		System.out.println(color.BLUE + "+---------------------+\n" + "| Enter Login Details |\n" + "+---------------------+" + color.RESET);
		System.out.println("User Id :" + color.GREEN);
		String id = sysin.nextLine();
		System.out.println(color.RESET + "Password :" + color.GREEN);
		String password = sysin.nextLine();

		Account account = dao.getAccount(id, password);
		return account;
	}

	public void printCustomerMenu() {
		System.out.println(color.BLUE + "+---------------------+\n" + "| WELCOME Customer!!! |\n" + "+---------------------+" + color.BLUE);
		System.out.println("1. Deposit Amount\n" + "2. Withdraw Amount\n" + "3. Funds Transfer\n" + "4. View 5 Recent Transactions\n" + "5. Display Customer Information\n" + "6. Sign Out\n");
		System.out.println(color.GREEN + "Enter Choice (1,2,3,4,5 or 6) :" + color.RESET);
	}

	public void printDepositMenu(Scanner sysin) {
		System.out.println(color.BLUE + "+---------+\n" + "| Deposit |\n" + "+---------+" + color.RESET);
		//TODO
		
	}

	public void printWithdrawMenu(Scanner sysin) {
		System.out.println(color.BLUE + "+----------+\n" + "| Withdraw |\n" + "+----------+" + color.RESET);
		//TODO
	}

	public void printFundsTransfer(Scanner sysin) {
		System.out.println(color.BLUE + "+----------------+\n" + "| Funds Transfer |\n" + "+----------------+" + color.RESET);
		//TODO
	}

	public void printRecentTransactions() {
		System.out.println(color.BLUE + "+------------------------+\n" + "| 5 Recent Transactions: |\n" + "+------------------------+" + color.RESET);
		//TODO
	}

	public void printCustomerInfo(Customer customer) {
		System.out.println(color.BLUE + "+----------------------+\n" + "| Customer Information |\n" + "+----------------------+" + color.RESET);
		System.out.println("Customer Name: " + color.GREEN + customer.getName());
		System.out.println(color.RESET + "Customer Address: " + color.GREEN + customer.getAddress());
		System.out.println(color.RESET + "Customer Contact Number: " + color.GREEN + customer.getPhone());
	}

	public void printError() {
		System.out.println(color.RED + "Invalid Input. Try Again!" + color.RESET);
	}

	public void printError(int error) {
		switch(error) {
			case 1:
				System.out.println(color.RED + "Invalid Selection. Try Again!" + color.RESET);
				break;
			case 2:
				System.out.println(color.RED + "Invalid Credentials. Try Again!" + color.RESET);
				break;
			default:
				System.out.println(color.RED + "Invalid Input. Try Again!" + color.RESET);
		}
	}
}