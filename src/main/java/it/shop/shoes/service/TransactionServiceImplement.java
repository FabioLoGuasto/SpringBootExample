package it.shop.shoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.shop.shoes.model.Transaction;
import it.shop.shoes.repository.TransactionRepository;

@Service
public class TransactionServiceImplement implements TransactionService{

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Override
	public Transaction insert(Transaction t) {
		Transaction transaction = new Transaction(t.getIdTransazione(), t.getFidelityNumber(), t.getClientId());
		return transactionRepository.save(transaction);
	}

	@Override
	public List<Transaction> getTransactions() {
		return transactionRepository.findAll();
	}

	@Override
	public void update(Long id, Transaction t) {
		t.setIdTransazione(id);
		transactionRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		transactionRepository.deleteById(id);
	}

}
