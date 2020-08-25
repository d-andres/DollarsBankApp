package com.dollarsbank.repository;

import com.dollarsbank.model.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AccountRepository extends JpaRepository<Account, Long>{

	@Query("SELECT a FROM Account a WHERE a.email=?1")
	Account findByEmail(String email);
	
	@Modifying
	@Transactional
	@Query("UPDATE Account a SET a.balance=?2 WHERE a.id=?1")
	void updateBalanceById(Long id, double balance);

	@Modifying
	@Transactional
	@Query("UPDATE Account a SET a.history=?2 WHERE a.id=?1")
	void updateHistoryById(Long id, String history);
}