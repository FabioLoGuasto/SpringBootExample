package it.shop.shoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.shop.shoes.model.Supplier;
import it.shop.shoes.repository.SupplierRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class SupplierServiceImplement implements SupplierService{

	@Autowired
	private SupplierRepository supplierRepository;
	
	/**
	 * insert new supplier
	 */
	@Override
	public Supplier insert(Supplier s) {
		Supplier supplier = new Supplier(s.getIdFornitore(), s.getSupplierCode(), s.getCompanyName(), s.getNation());
		return supplierRepository.save(supplier);
	}

	/**
	 * get list of suppliers
	 */
	@Override
	public List<Supplier> getSuppliers() {
		return supplierRepository.findAll();
	}

	/**
	 * update field of supplier by id
	 */
	@Override
	public void update(Long id, Supplier s) {
		s.setIdFornitore(id);
		supplierRepository.save(s);
		
	}

	/**
	 * delete supplier by id
	 */
	@Override
	public void delete(Long id) {
		supplierRepository.deleteById(id);
	}



}
