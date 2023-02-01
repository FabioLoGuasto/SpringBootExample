package it.shop.shoes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.shop.shoes.model.Supplier;

@Service
public interface SupplierService {
	
	public Supplier insert(Supplier s); 
	public List<Supplier> getSuppliers();
	public void update(Long id,Supplier s);
	public void delete (Long id);

}
