package it.shop.shoes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.shop.shoes.model.Transaction;

@Service
public interface TransactionService {

	public Transaction insert(Transaction s); 
	public List<Transaction> getTransactions();
	public void update(Long id,Transaction s);
	public void delete (Long id);
	
}
