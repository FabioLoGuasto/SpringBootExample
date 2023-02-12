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

import it.shop.shoes.model.Shop;
import it.shop.shoes.service.ShopService;

@RestController
@RequestMapping(value = "api/shop")
public class ControllerShop {
	
	@Autowired ShopService shopService;
	
	public static final Logger logger = LoggerFactory.getLogger(ControllerShop.class);
	
	/**
	 * localhost:8080/api/shop/getAllShop
	 * This method return a list with all shops
	 * @return listShop
	 */
	@GetMapping(path ="/getAllShop", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<Shop>> getAllShop(){
		logger.info("GET ALL SHOPS");
		try {
			List<Shop> listShop = shopService.getShops();
			return new ResponseEntity <List<Shop>> (listShop,HttpStatus.OK);
		}catch(Exception e) {
			logger.error("ERROR " + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	/**
	 * localhost:8080/api/shop/insertShop
	 * This method insert a new shop
	 * @param shop : Shop Object
	 * @return insertShop : return new shop
	 */
	@PostMapping(path ="/insertShop", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Shop> insertShop(@RequestBody Shop shop){
		logger.info("INSERT NEW SHOP");
		try {
			Shop insertShop = shopService.insert(shop);
			logger.info("INSERT NEW SHOP OK");
			return new ResponseEntity<Shop>(insertShop,HttpStatus.CREATED);
		}catch (Exception e) {
			logger.error("ERROR: \n", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}  
	}
	
	
	/**
	 * localhost:8080/api/shop/updateShopById/
	 * This method update one field of Shop by idShop
	 * @param id : id of selected shop
	 * @param shop : Shop Object
	 * @return String
	 */
	@PutMapping("/updateShopById/{id}")
	public ResponseEntity <String> updateShopById(@PathVariable("id") Long id, @RequestBody Shop shop){
		logger.info("UPDATE SHOP BY ID");
		 try { 
			 shopService.update(id, shop);
			 return new ResponseEntity<String>("SHOP MODIFIED",HttpStatus.OK);
		 }catch(Exception e) {			 
			 logger.error("ERROR: \n", e);
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		 }
	}
	
	/**
	 * localhost:8080/api/shop/deleteShopById/
	 * This method delete one field by idShop
	 * @param id : id of selected Shop
	 * @return String
	 */
	@DeleteMapping(path = "/deleteShopById/{id}")
	public ResponseEntity <String> deleteShopById(@PathVariable("id") Long id){
		logger.info("DELETE ONE SHOP");
		try {
			shopService.delete(id);
			logger.info("SHOP " + id + " DELETE !!!");
			return new ResponseEntity <String>("DELETE",HttpStatus.OK);
		}catch(Exception e) {
			  logger.error("ERROR: \n", e);
			  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		  }
	}
	
}
