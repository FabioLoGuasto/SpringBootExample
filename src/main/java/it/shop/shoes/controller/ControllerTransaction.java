package it.shop.shoes.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.shop.shoes.model.Transaction;
import it.shop.shoes.service.TransactionService;

@RestController
@RequestMapping(value = "api/transaction")
public class ControllerTransaction {

	@Autowired TransactionService transactionService;
	
	
	public static final Logger logger = LoggerFactory.getLogger(ControllerTransaction.class);
	
	
	/**
	 * localhost:8080/api/transaction/getAllTransactions
	 * This method return a list with all transactions
	 * @return listTransactions
	 */
	@GetMapping(path ="/getAllTransactions", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<Transaction>> getAllTransactions(){
		logger.info("GET ALL TRANSACTIONS");
		try {
			List<Transaction> listTransactions = transactionService.getTransactions();
			return new ResponseEntity <List<Transaction>> (listTransactions,HttpStatus.OK);
		}catch(Exception e) {
			logger.error("ERROR " + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	/**
	 * localhost:8080/api/transaction/insertTransaction
	 * This method insert a new transaction
	 * @param transaction : Transaction Object
	 * @return insertTransaction : return new transaction
	 */
	@PostMapping(path ="/insertTransaction", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Transaction> insertTransaction(@RequestBody Transaction transaction){
		logger.info("INSERT NEW TRANSACTION");
		try {
			Transaction insertTransaction = transactionService.insert(transaction);
			logger.info("INSERT NEW TRANSACTION OK");
			return new ResponseEntity<Transaction>(insertTransaction,HttpStatus.CREATED);
		}catch (Exception e) {
			logger.error("ERROR: \n", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}  
	}
	
	
	/**
	 * localhost:8080/api/transaction/updateTransactionById/
	 * This method update one field of Supplier by idTransaction
	 * @param id : id of selected transaction
	 * @param transaction : Transaction Object
	 * @return String
	 */
	@PutMapping("/updateTransactionById/{id}")
	public ResponseEntity <String> updateTransactionById(@PathVariable("id") Long id, @RequestBody Transaction transaction){
		logger.info("UPDATE TRANSACION BY ID");
		 try { 
			 transactionService.update(id, transaction);
			 return new ResponseEntity<String>("TRANSACTION MODIFIED",HttpStatus.OK);
		 }catch(Exception e) {			 
			 logger.error("ERROR: \n", e);
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		 }
	}
	
	/**
	 * localhost:8080/api/transaction/deleteTransactionById/
	 * This method delete one field by idTransaction
	 * @param id : id of selected transaction
	 * @return String
	 */
	@DeleteMapping(path = "/deleteTransactionById/{id}")
	public ResponseEntity <String> deleteTransactionById(@PathVariable("id") Long id){
		logger.info("DELETE ONE TRANSACTION");
		try {
			transactionService.delete(id);
			logger.info("TRANSACTION " + id + " DELETE !!!");
			return new ResponseEntity <String>("DELETE",HttpStatus.OK);
		}catch(Exception e) {
			  logger.error("ERROR: \n", e);
			  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		  }
	}
	
}