package it.shop.shoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.shop.shoes.model.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long>{
	
//	@Query("select distinct s FROM supplier s left join fetch s.article")
//	List<Supplier> findAllSuppliers();

}
