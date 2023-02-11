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

import it.shop.shoes.model.Shop;
import it.shop.shoes.service.ShopService;

@RestController
@RequestMapping(value = "api/shoes")
public class ControllerShoes {
	
	@Autowired ShopService shopService;
	
	public static final Logger logger = LoggerFactory.getLogger(ControllerShoes.class);
	
	/**
	 * localhost:8080/api/shoes/getAllShop
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
	
	
	
}
