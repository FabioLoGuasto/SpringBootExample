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

import it.shop.shoes.model.Supplier;
import it.shop.shoes.service.SupplierService;

@RestController
@RequestMapping(value = "api/supplier")
public class ControllerSupplier {

	
	public static final Logger logger = LoggerFactory.getLogger(ControllerSupplier.class);
	@Autowired SupplierService supplierService;
	
	
	/**
	 * localhost:8080/api/supplier/getAllSuppliers
	 * This method return a list with all suppliers
	 * @return listSuppliers
	 */
	@GetMapping(path ="/getAllSuppliers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<Supplier>> getAllSuppliers(){
		logger.info("GET ALL SUPPLIER");
		try {
			List<Supplier> listSuppliers = supplierService.getSuppliers();
			return new ResponseEntity <List<Supplier>> (listSuppliers,HttpStatus.OK);
		}catch(Exception e) {
			logger.error("ERROR " + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	/**
	 * localhost:8080/api/supplier/insertSupplier
	 * This method insert a new Supplier
	 * @param supplier : Supplier Object
	 * @return insertSupplier : return new supplier
	 */
	@PostMapping(path ="/insertSupplier", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Supplier> insertSupplier(@RequestBody Supplier supplier){
		logger.info("INSERT NEW SUPPLIER");
		try {
			Supplier insertSupplier = supplierService.insert(supplier);
			logger.info("INSERT NEW SUPPLIER OK");
			return new ResponseEntity<Supplier>(insertSupplier,HttpStatus.CREATED);
		}catch (Exception e) {
			logger.error("ERROR: \n", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}  
	}
	
	
	/**
	 * localhost:8080/api/supplier/updateSupplierById/
	 * This method update one field of Supplier by idSupplier
	 * @param id : id of Supplier Object
	 * @param supplier : Supplier Object
	 * @return String
	 */
	@PutMapping("/updateSupplierById/{id}")
	public ResponseEntity <String> updateSupplierById(@PathVariable("id") Long id, @RequestBody Supplier supplier){
		logger.info("UPDATE SUPPLIER BY ID");
		 try { 
			 supplierService.update(id, supplier);
			 return new ResponseEntity<String>("SUPPLIER MODIFIED",HttpStatus.OK);
		 }catch(Exception e) {			 
			 logger.error("ERROR: \n", e);
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		 }
	}
	
	
	/**
	 * localhost:8080/api/supplier/deleteSupplierById/
	 * This method delete one field by idSupplier
	 * @param id : id of selected Supplier
	 * @return String
	 */
	@DeleteMapping(path = "/deleteSupplierById/{id}")
	public ResponseEntity <String> deleteSupplierById(@PathVariable("id") Long id){
		logger.info("DELETE ONE SUPPLIER");
		try {
			supplierService.delete(id);
			logger.info("SUPPLIER " + id + " DELETE !!!");
			return new ResponseEntity <String>("DELETE",HttpStatus.OK);
		}catch(Exception e) {
			  logger.error("ERROR: \n", e);
			  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		  }
	}
	
	
}
