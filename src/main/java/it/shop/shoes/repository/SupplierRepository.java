package it.shop.shoes.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.shop.shoes.model.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long>{
	

}
