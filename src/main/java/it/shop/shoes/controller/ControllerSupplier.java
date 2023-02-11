package it.shop.shoes.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	
}
