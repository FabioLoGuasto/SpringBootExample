package it.shop.shoes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.shop.shoes.model.Transaction;

@Service
public interface TransactionService {

	/**
	 * insert new transaction
	 * @param transaction
	 * @return new transaction
	 */
	public Transaction insert(Transaction transaction); 
	
	/**
	 * get list of transaction
	 * @return list
	 */
	public List<Transaction> getTransactions();
	
	/**
	 * update fields of one transaction
	 * @param id : id of transaction
	 * @param transaction
	 */
	public void update(Long id,Transaction transaction);
	
	/**
	 * delete one transaction by id
	 * @param id : id of trasaction
	 */
	public void delete (Long id);
	

	
}
