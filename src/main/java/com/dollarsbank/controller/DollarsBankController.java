package com.dollarsbank.controller;

import java.util.List;
import java.util.Scanner;

import com.dollarsbank.model.Account;
import com.dollarsbank.utility.ConsolePrinterUtility;
import com.dollarsbank.utility.DollarsBankDAO;

public class DollarsBankController {

	List<Account> accountList;
	DollarsBankDAO dao = new DollarsBankDAO();

	public DollarsBankController() {
		accountList = dao.getAllAccounts();
	}

	public void run() {
		ConsolePrinterUtility printer = new ConsolePrinterUtility();
		Scanner sysin = new Scanner(System.in);
		String input;
		

		while(true) {
			printer.printWelcome();
			input = sysin.nextLine();
			switch(input) {
				case "1":
					printer.printNewAccount(sysin);
					break;
				case "2":
					printer.printLogin(sysin);
					break;
				case "3":
					return;
				default:
					printer.printError(1);
			}
		}
	}

}