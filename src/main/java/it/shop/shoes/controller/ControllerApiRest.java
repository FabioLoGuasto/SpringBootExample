package it.shop.shoes.controller;


import java.util.List;
import java.util.Scanner;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.shop.shoes.dto.ArticleDto;
import it.shop.shoes.dto.ArticleDtoExample;
import it.shop.shoes.dto.RicercaDto;
import it.shop.shoes.model.Article;
import it.shop.shoes.model.FidelityClient;
import it.shop.shoes.model.Shop;
import it.shop.shoes.model.Supplier;
import it.shop.shoes.model.Transaction;
import it.shop.shoes.service.ArticleService;
import it.shop.shoes.service.FidelityClientService;
import it.shop.shoes.service.ShopService;
import it.shop.shoes.service.SupplierService;
import it.shop.shoes.service.TransactionService;

/**
 * @author fabio
 * Class used for map API endPoint
 */
@RestController
@RequestMapping(value = "/api")
public class ControllerApiRest {
		
	public static final Logger logger = LoggerFactory.getLogger(ControllerApiRest.class);
	
	@Autowired ArticleService articleService;
	@Autowired ShopService shopService;
	@Autowired SupplierService supplierService;
	@Autowired TransactionService transactionService;
	@Autowired FidelityClientService fidelityClientService;
// ---------------------------------------------------------------------------------- ARTICLE	
	
	/**
	 * localhost:8080/api/researchForBrand?primoParametro=ADIDAS
	 * This method return a list with all articles from one selected brand
	 * @param brand : chosen brand
	 * @return listArticles
	 */
	@GetMapping(path ="/researchForBrand", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<Article>> researchForBrand(@RequestParam(value = "primoParametro") String brand){
		logger.info("GET ALL ARTICLES WITH SELECTED BRAND");
		try {
			List<Article> listArticles = articleService.researchForBrand(brand);
			return new ResponseEntity <List<Article>> (listArticles,HttpStatus.OK);
		}catch(Exception e) {
			logger.error("ERROR " + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	/**
	 * localhost:8080/api/ricercaDto?primoParametro=2&secondoParametro=A008
	 * @param negozioId : chosen field negozioId 
	 * @param codice : chosen field codice
	 * @return listDto :this method return a list with the selected field from RicercaDto
	 */
	@GetMapping(path = "/ricercaDto", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<RicercaDto>> ricercaDto (@RequestParam(value = "primoParametro") int negozioId, @RequestParam(value = "secondoParametro") String codice){
		logger.info("RICERCA DTO");
		try {
			List<Article> listArticle = articleService.ricerca(negozioId, codice);
			List<RicercaDto> listDto = articleService.ricercaDto(listArticle);
			return new ResponseEntity<List<RicercaDto>>(listDto, HttpStatus.OK);
		}catch(Exception e) {
			logger.error("ERROR: \n", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	/**
	 * localhost:8080/api/getAllArticles
	 * This method return a list with all articles from all shops
	 * @return listArticles
	 */
	@GetMapping(path ="/getAllArticles", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<Article>> getAllArticles(){
		logger.info("GET ALL ARTICLES");
		try {
			List<Article> listArticles = this.articleService.getArticles();
			return new ResponseEntity <List<Article>> (listArticles,HttpStatus.OK);
		}catch(Exception e) {
			logger.error("ERROR " + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}	
	
	/**
	 * https://www.youtube.com/watch?v=SG2gfTPzSQE
	 * localhost:8080/api/insertArticleDto
	 * This is the method for insert new Article.
	 * @param dto : Object ArticleDto
	 * @return articleDto : return new Article
	 */
	@PostMapping(path ="/insertArticleDto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArticleDto> insertArticleDto(@RequestBody ArticleDto dto){
		logger.info("INSERT ARTICLE");
		try {
			Article art = articleService.dtoToEntity(dto);
			art = articleService.insert(art);
			ArticleDto articleDto = articleService.EntityToDto(art);
			logger.info("INSERT OK");
			return new ResponseEntity<ArticleDto>(articleDto,HttpStatus.CREATED);
		}catch (Exception e) {
			logger.error("ERROR: \n", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}  
	}


	/**
	 * https://www.youtube.com/watch?v=SG2gfTPzSQE
	 * localhost:8080/api/getAllArticlesDtoExample
	 * This method return a list ArticleDtoExample (only code & brand of Article)
	 * @return listArticlesDto 
	 */
	@GetMapping(path ="/getAllArticlesDtoExample", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<ArticleDtoExample>> getAllArticlesDtoExample(){
		logger.info("GET ALL ARTICLES WITH DTOEXAMPLE");
		try {
			List<Article> listArticles = this.articleService.getArticles();
			List<ArticleDtoExample> listArticlesDto = articleService.questCodeAndBrand(listArticles);
			return new ResponseEntity <List<ArticleDtoExample>> (listArticlesDto,HttpStatus.OK);
		}catch(Exception e) {
			logger.error("ERROR " + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	/**
	 * localhost:8080/api/updateArticleById/
	 * This method update one field of article by idArticolo
	 * @param id : field id of Article
	 * @param art : Object Article
	 * @return String 
	 */
	@PutMapping("/updateArticleById/{id}")
	public ResponseEntity <String> updateArticleById(@PathVariable("id") Long id, @RequestBody Article art){
		logger.info("UPDATE ARTICLE BY ID");
		 try { 
			 articleService.update(id, art);
			 return new ResponseEntity<String>("ARTICLE MODIFIED",HttpStatus.OK);
		 }catch(Exception e) {			 
			 logger.error("ERROR: \n", e);
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		 }
	}
	
	/**
	 * localhost:8080/api/deleteArticleById/
	 * This method delete one field by idArticolo
	 * @param id : id of Article
	 * @return String 
	 */
	@DeleteMapping(path = "/deleteArticleById/{id}")
	public ResponseEntity <String> deleteArticleById(@PathVariable("id") Long id){
		logger.info("DELETE ONE ROW");
		try {
			articleService.delete(id);
			logger.info("ARTICLE " + id + " DELETE !!!");
			return new ResponseEntity <String>("DELETE",HttpStatus.OK);
		}catch(Exception e) {
			  logger.error("ERROR: \n", e);
			  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		  }
	}
	
	
//----------------------------------------------------------------------------------------------------------------------------- SHOP	
	
	/**
	 * localhost:8080/api/getAllShop
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
	 * localhost:8080/api/insertShop
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
	 * localhost:8080/api/updateShopById/
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
	 * localhost:8080/api/deleteShopById/
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
	
	
// ------------------------------------------------------------------------------------ SUPPLIER
	
	/**
	 * localhost:8080/api/getAllSuppliers
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
	 * localhost:8080/api/insertSupplier
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
	 * localhost:8080/api/updateSupplierById/
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
	 * localhost:8080/api/deleteSupplierById/
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
	
	
// --------------------------------------------------------------------------------------------------- TRANSACTION	
	
	
	/**
	 * localhost:8080/api/getAllTransactions
	 * This method return a list with all transactions
	 * @return listTransactions
	 */
	@GetMapping(path ="/getAllTransactions", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<Transaction>> getAllTransactions(){
		logger.info("GET ALL TRANSACTIONS");
		try {
			List<Transaction> listTransactions = transactionService.getTransactions();
			return new ResponseEntity <List<Transaction>> (listTransactions,HttpStatus.OK);
		}catch(Exception e) {
			logger.error("ERROR " + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	/**
	 * localhost:8080/api/insertTransaction
	 * This method insert a new transaction
	 * @param transaction : Transaction Object
	 * @return insertTransaction : return new transaction
	 */
	@PostMapping(path ="/insertTransaction", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Transaction> insertTransaction(@RequestBody Transaction transaction){
		logger.info("INSERT NEW TRANSACTION");
		try {
			Transaction insertTransaction = transactionService.insert(transaction);
			logger.info("INSERT NEW TRANSACTION OK");
			return new ResponseEntity<Transaction>(insertTransaction,HttpStatus.CREATED);
		}catch (Exception e) {
			logger.error("ERROR: \n", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}  
	}
	
	
	/**
	 * localhost:8080/api/updateTransactionById/
	 * This method update one field of Supplier by idTransaction
	 * @param id : id of selected transaction
	 * @param transaction : Transaction Object
	 * @return String
	 */
	@PutMapping("/updateTransactionById/{id}")
	public ResponseEntity <String> updateTransactionById(@PathVariable("id") Long id, @RequestBody Transaction transaction){
		logger.info("UPDATE TRANSACION BY ID");
		 try { 
			 transactionService.update(id, transaction);
			 return new ResponseEntity<String>("TRANSACTION MODIFIED",HttpStatus.OK);
		 }catch(Exception e) {			 
			 logger.error("ERROR: \n", e);
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		 }
	}
	
	/**
	 * localhost:8080/api/deleteTransactionById/
	 * This method delete one field by idTransaction
	 * @param id : id of selected transaction
	 * @return String
	 */
	@DeleteMapping(path = "/deleteTransactionById/{id}")
	public ResponseEntity <String> deleteTransactionById(@PathVariable("id") Long id){
		logger.info("DELETE ONE TRANSACTION");
		try {
			transactionService.delete(id);
			logger.info("TRANSACTION " + id + " DELETE !!!");
			return new ResponseEntity <String>("DELETE",HttpStatus.OK);
		}catch(Exception e) {
			  logger.error("ERROR: \n", e);
			  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		  }
	}
	
	
	
// -------------------------------------------------------------------------------------------------- FIDELITY CLIENT	
	
	/**
	 * localhost:8080/api/getAllFidelityClients
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
	 * localhost:8080/api/insertFidelityClient
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
	 * localhost:8080/api/updateFidelityClientById/
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
	 * localhost:8080/api/deleteFidelityClientById/
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
	
// ----------------------------------------------------------------------------------------------- METODI VARI
	
	
	
	/**
	 * localhost:8080/api/insertTransazioneUpdatevenduto
	 * This method insert a new transaction and update the sellOut of selected idArticolo.
	 * This is a possibiliy example of sull of one article
	 * @param transaction : Transaction Object
	 * @return : new trasaction and update field sellOut of Article of selected idArticle
	 */
	@PostMapping(path = "/insertTransazioneUpdatevenduto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <Transaction> insertTransazioneUpdatevenduto(@RequestBody Transaction transaction){
		Long idTransaction = (long) 0;
		Transaction insertTransaction;
		Scanner s = new Scanner(System.in);
		
		try {
			insertTransaction = transactionService.insert(transaction);
			idTransaction = insertTransaction.getIdTransazione();
			logger.info("INSERT A NEW TRANSACTION OK !!");
		}catch(Exception e) {
			  logger.error("ERROR: \n", e);
			  s.close();
			  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		try {
			logger.info("UPDATE SELL OUT ARTICLE");
			System.out.println("INSERISCI L'ID DELL'ARTICOLO VENDUTO");
			Long idArticolo = s.nextLong();
			articleService.updateSellOutArticle(idTransaction, 0, idArticolo);
			logger.info("ARTICOLO VENDUTO, TRANSAZIONE TERMINATA !!");
			s.close();
			return new ResponseEntity <Transaction>(insertTransaction,HttpStatus.OK);
		}catch(Exception e) {
			  logger.error("ERROR: \n", e);
			  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		
	}
	
	
	
	
	
}
