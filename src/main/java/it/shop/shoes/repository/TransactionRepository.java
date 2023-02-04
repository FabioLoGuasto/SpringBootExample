package it.shop.shoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.shop.shoes.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long>{

}
