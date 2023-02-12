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

import it.shop.shoes.model.FidelityClient;
import it.shop.shoes.service.FidelityClientService;

@RestController
@RequestMapping(value = "api/fidelityClient")
public class ControllerFidelityClient {
	
	@Autowired FidelityClientService fidelityClientService;
	public static final Logger logger = LoggerFactory.getLogger(ControllerFidelityClient.class);

	
	/**
	 * localhost:8080/api/fidelityClient/getAllFidelityClients
	 * This method return a list with all Fidelity Clients
	 * @return listFidelityClients
	 */
	@GetMapping(path ="/getAllFidelityClients", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<FidelityClient>> getAllFidelityClients(){
		logger.info("GET ALL FIDELITY CLIENT");
		try {
			List<FidelityClient> listFidelityClients = fidelityClientService.getFidelityClients();
			return new ResponseEntity <List<FidelityClient>> (listFidelityClients,HttpStatus.OK);
		}catch(Exception e) {
			logger.error("ERROR " + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	/**
	 * localhost:8080/api/fidelityClient/insertFidelityClient
	 * This method insert a new fidelity client
	 * @param fidelityClient : FidelityClient Object
	 * @return insertFidelityClient : return new fidelityClient
	 */
	@PostMapping(path ="/insertFidelityClient", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FidelityClient> insertFidelityClient(@RequestBody FidelityClient fidelityClient){
		logger.info("INSERT NEW FIDELITY CLIENT");
		try {
			FidelityClient insertFidelityClient = fidelityClientService.insert(fidelityClient);
			logger.info("INSERT NEW FIDELITY CLIENT OK");
			return new ResponseEntity<FidelityClient>(insertFidelityClient,HttpStatus.CREATED);
		}catch (Exception e) {
			logger.error("ERROR: \n", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}  
	}
	
	
	/**
	 * localhost:8080/api/fidelityClient/updateFidelityClientById/
	 * This method update one field of fidelityClient by idClient
	 * @param id : id of selected field
	 * @param fidelityClient : FidelityClient Object
	 * @return String
	 */
	@PutMapping("/updateFidelityClientById/{id}")
	public ResponseEntity <String> updateFidelityClientById(@PathVariable("id") Long id, @RequestBody FidelityClient fidelityClient){
		logger.info("UPDATE FIDELITY CLIENT BY ID");
		 try { 
			 fidelityClientService.update(id, fidelityClient);
			 return new ResponseEntity<String>("FIDELITY CLIENT MODIFIED",HttpStatus.OK);
		 }catch(Exception e) {			 
			 logger.error("ERROR: \n", e);
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		 }
	}
	
	/**
	 * localhost:8080/api/fidelityClient/deleteFidelityClientById/
	 * This method delete one field by idClient
	 * @param id : id of selected client
	 * @return String
	 */
	@DeleteMapping(path = "/deleteFidelityClientById/{id}")
	public ResponseEntity <String> deleteFidelityClientById(@PathVariable("id") Long id){
		logger.info("DELETE ONE FIDELITY CLIENT");
		try {
			fidelityClientService.delete(id);
			logger.info("FIDELITY CLIENT " + id + " DELETE !!!");
			return new ResponseEntity <String>("DELETE",HttpStatus.OK);
		}catch(Exception e) {
			  logger.error("ERROR: \n", e);
			  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		  }
	}
}
