package com.dollarsbank.controller;

import com.dollarsbank.model.Account;
import com.dollarsbank.repository.AccountRepository;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
    
    private final AccountRepository accountRepository;

	public AppController(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@RequestMapping("/")
    String getHome(HttpSession session) {
        if(session.getAttribute("activeAccount") != null)
            return "views/home";
        return "redirect:/login";
    }

    @GetMapping("/login")
    String getLogin(Model model) {
        model.addAttribute("account", new Account());
        return "views/login/login_form";
    }

    @PostMapping("/login")
    String postLogin(@ModelAttribute Account account, HttpSession session) {
        if(account.getEmail() != null && account.getPassword() != null) {
            Account a = new Account();
            a = accountRepository.findByEmail(account.getEmail());
            if(a.getId() != null) {
                if (a.getHistory() == null)
                    a.setHistory("");
                System.out.println(a.getName() + " has logged in.");
                session.setAttribute("activeAccount", a);
            }
        }
        return "views/login/login_confirm";
    }

    @GetMapping("/register")
    String getRegister(Model model) {
        model.addAttribute("account", new Account());
        return "views/register/register_form";
    }

    @PostMapping("/register")
    String postRegister(@ModelAttribute Account acct, HttpSession session) {
        if(acct.getName() != null && acct.getAddress() != null && acct.getPhone() != null && acct.getEmail() != null && acct.getPassword() != null) {
            accountRepository.save(acct);
            System.out.println("New account created.");
            System.out.println("Number of Accounts: " + accountRepository.count());
            session.setAttribute("registered", true);
        } else
            session.setAttribute("registered", false);
            
        return "views/register/register_confirm";
    }

    @RequestMapping("/about")
    String showAbout() {
        return "views/information/about";
    }

    @RequestMapping("/contact")
    String showContact() {
        return "views/information/contact";
    }

    @RequestMapping("/logout")
    String showLogout(HttpSession session) {
        if(session.getAttribute("activeAccount") != null)
            session.removeAttribute("activeAccount");
        return "views/login/logout";
    }

    @GetMapping("/deposit")
    String getDeposit(Model model) {
        model.addAttribute("account", new Account());
        return "views/transactions/deposit_form";
    }

    @PostMapping("/deposit")
    String postDeposit(@ModelAttribute Account money, HttpSession session) {
        double deposit = money.getBalance();
        if (deposit > 0) {
            Account account = (Account)session.getAttribute("activeAccount");
            double newbalance = account.getBalance() + deposit;
            account.setBalance(newbalance);
            accountRepository.updateBalanceById(account.getId(), newbalance);
            System.out.println(account.getName() + " has deposited $" + deposit);
            String history = account.getHistory() + "+ $" + deposit + " on " + new java.util.Date() + "|";
            account.setHistory(history);
            accountRepository.updateHistoryById(account.getId(), history);
            session.setAttribute("activeAccount", account);
            session.setAttribute("deposited", true);
            return "views/transactions/deposit_confirm";
        }
        session.setAttribute("deposited", false);
        return "views/transactions/deposit_confirm";
    }

    @GetMapping("/withdraw")
    String getWithdraw(Model model) {
        model.addAttribute("account", new Account());
        return "views/transactions/withdraw_form";
    }

    @PostMapping("/withdraw")
    String postWithdraw(@ModelAttribute Account money, HttpSession session) {
        double withdraw = money.getBalance();
        if (withdraw > 0) {
            Account account = (Account)session.getAttribute("activeAccount");
            double newbalance = account.getBalance() - withdraw;
            if(newbalance >= 0) {
                account.setBalance(newbalance);
                accountRepository.updateBalanceById(account.getId(), newbalance);
                System.out.println(account.getName() + " has withdrew $" + withdraw);
                String history = account.getHistory() + "- $" + withdraw + " on " + new java.util.Date() + "|";
                account.setHistory(history);
                accountRepository.updateHistoryById(account.getId(), history);
                session.setAttribute("activeAccount", account);
                session.setAttribute("withdrew", true);
                return "views/transactions/withdraw_confirm";
            }
            
        }
        session.setAttribute("withdrew", false);
        return "views/transactions/withdraw_confirm";
    }

    @GetMapping("/transfer")
    String getTransfer(Model model) {
        model.addAttribute("account", new Account());
        return "views/transactions/transfer_form";
    }

    @PostMapping("/transfer")
    String postTransfer(@ModelAttribute Account acct, HttpSession session) {
        session.setAttribute("transfered", false);
        return "views/transactions/transfer_confirm";
    }

    @RequestMapping("/recent-history")
    String showHistory() {
        return "views/information/history";
    }

    @RequestMapping("/customer-info")
    String showCustomerInfo() {
        return "views/information/customer-info";
    }
}