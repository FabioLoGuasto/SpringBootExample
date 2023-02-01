package it.shop.shoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.shop.shoes.model.Supplier;
import it.shop.shoes.repository.SupplierRepository;

@Service
public class SupplierServiceImplement implements SupplierService{

	@Autowired
	private SupplierRepository supplierRepository;
	
	@Override
	public Supplier insert(Supplier s) {
		Supplier supplier = new Supplier(s.getId_fornitore(), s.getSupplierCode(), s.getCompanyName(), s.getNation());
		return supplierRepository.save(supplier);
	}

	@Override
	public List<Supplier> getSuppliers() {
		return supplierRepository.findAll();
	}

	@Override
	public void update(Long id, Supplier s) {
		s.setId_fornitore(id);
		supplierRepository.save(s);
		
	}

	@Override
	public void delete(Long id) {
		supplierRepository.deleteById(id);
	}

}
