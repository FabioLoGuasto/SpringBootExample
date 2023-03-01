package it.shop.shoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.shop.shoes.model.Supplier;
import jakarta.transaction.Transactional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long>{
	
	/**
	 * 
	 * @return
	 */
	@Transactional
	@Query("SELECT s FROM Supplier s JOIN FETCH s.listArticlesOfSupplied")
    public List<Supplier> queryGetAllSupplierFetchArticle();

}
