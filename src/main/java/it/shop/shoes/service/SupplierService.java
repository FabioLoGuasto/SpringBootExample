package it.shop.shoes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.shop.shoes.model.Supplier;

@Service
public interface SupplierService {
	
	/**
	 * insert new supplier
	 * @param supplier
	 * @return new supplier
	 */
	public Supplier insert(Supplier supplier); 
	
	/**
	 * get list of supplier
	 * @return list
	 */
	public List<Supplier> getSuppliers();
	
	/**
	 * update one field of one supplier by id
	 * @param id : id of supplier
	 * @param supplier
	 */
	public void update(Long id,Supplier supplier);
	
	/**
	 * delete one supplier by id
	 * @param id : id of supplier
	 */
	public void delete (Long id);

}
