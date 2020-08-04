package com.dollarsbank.controller;

import java.util.Scanner;

import com.dollarsbank.model.Account;
import com.dollarsbank.utility.ConsolePrinterUtility;
import com.dollarsbank.utility.DollarsBankDAO;

/**
 * 
 */
public class DollarsBankController {

	DollarsBankDAO dao = new DollarsBankDAO();

	/**
	 * 
	 */
	public void run() {
		ConsolePrinterUtility printer = new ConsolePrinterUtility();
		Scanner sysin = new Scanner(System.in);
		String choice = "";
		

		while(!choice.equals("3")) {
			//show Welcome Menu
			printer.printWelcome();
			choice = sysin.nextLine();
			switch(choice) {
				case "1":
					//show New Account Menu
					Account account = printer.printNewAccount(sysin);
					dao.addAccount(account);
					//account = dao.getAccount(account.getCustomer().getId(), account.getCustomer().getPassword());
					dao.addSavings(dao.getAccount(account.getCustomer().getId(), account.getCustomer().getPassword()).getAccountNumber());
					break;
				case "2":
					while(true) {
						//show Login Menu
						Account acct = printer.printLogin(sysin, dao);
						if(acct == null)
							printer.printError(2);
						else {
							String choice1 = "";
							while(!choice1.equals("6")) {
								//show Customer Menu
								printer.printCustomerMenu();
								choice1 = sysin.nextLine();
								switch(choice1) {
									case "1":
										//show Deposit Menu
										printer.printDepositMenu(sysin, acct);
										break;
									case "2":
										//show Withdraw Menu
										printer.printWithdrawMenu(sysin, acct);
										break;
									case "3":
										//show Funds Transfer Menu
										printer.printFundsTransfer(sysin, acct);
										break;
									case "4":
										//show 5 Recent Transactions
										printer.printRecentTransactions(acct);
										break;
									case "5":
										//show Customer Info
										printer.printCustomerInfo(acct.getCustomer());
										sysin.nextLine();
										break;
									case "6":
										//quits Customer Menu after update db
										dao.updateAccount(acct);
										dao.updateSavings(acct.getSavingsAccount());
										break;
									default:
										printer.printError(1);
								}
							}
							break;
						}
					}
					break;
				case "3":
					//Exits app
					break;
				default:
					printer.printError(1);
			}
		}
		sysin.close();
	}

}