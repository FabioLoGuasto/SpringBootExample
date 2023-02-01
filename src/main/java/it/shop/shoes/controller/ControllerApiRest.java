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

import it.shop.shoes.dto.ArticleDto;
import it.shop.shoes.model.Article;
import it.shop.shoes.model.Shop;
import it.shop.shoes.model.Supplier;
import it.shop.shoes.service.ArticleService;
import it.shop.shoes.service.ShopService;
import it.shop.shoes.service.SupplierService;

/**
 * @author fabio
 * Class used for map API endPoint
 */
@RestController
@RequestMapping(value = "/api")
public class ControllerApiRest {
	
	/**
	 * try controller
	 * @return
	 */
	@RequestMapping("/")
	public String welcomepage2() {
		return "funziona /";
	}
	@GetMapping("/a")
	public String welcomepage2sssss() {
		return "funziona A";
	}
		
	public static final Logger logger = LoggerFactory.getLogger(ControllerApiRest.class);
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	ShopService shopService;
	
	@Autowired
	SupplierService supplierService;
	
// ---------------------------------------------------------------------------------- ARTICLE	
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
	 * localhost:8080/api/insertArticle
	 * al momento è in uso il costruttore senza transazione_id
	 * @param art
	 * @return
	 */
	@PostMapping(path ="/insertArticle", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Article> insertArticle(@RequestBody Article art){
		logger.info("INSERT ARTICLE");
		try {
			Article insArticle = articleService.insert(art);
			logger.info("INSERT OK");
			return new ResponseEntity<Article>(insArticle,HttpStatus.CREATED);
		}catch (Exception e) {
			logger.error("ERROR: \n", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}  
	}
	
	
	/**
	 * https://www.youtube.com/watch?v=SG2gfTPzSQE
	 * localhost:8080/api/insertArticleDto
	 * per farlo funzionare ho cmq bisogno di un costruttore senza transazione_id nella entity Article
	 * al momento è in uso il costruttore senza transazione_id
	 * @param art
	 * @return
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
	 * localhost:8080/api/getAllArticlesDto
	 * This method return a list ArticleDto (transaction_id excluse) with all articles from all shops
	 * @return listArticles
	 */
	@GetMapping(path ="/getAllArticlesDto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<ArticleDto>> getAllArticlesDto(){
		logger.info("GET ALL ARTICLES WITH DTO");
		try {
			List<Article> listArticles = this.articleService.getArticles();
			List<ArticleDto> listArticlesDto = articleService.getAllArticleDto(listArticles);
			return new ResponseEntity <List<ArticleDto>> (listArticlesDto,HttpStatus.OK);
		}catch(Exception e) {
			logger.error("ERROR " + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	/**
	 * localhost:8080/api/updateArticleById/
	 * this method update one field of article by id_articolo
	 * @param id
	 * @param art
	 * @return
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
	 * this method delete one field by id_articolo
	 * @param id
	 * @return
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
	
	
//------------------------------------------------------------------------------ SHOP	
	
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
	 * @param 
	 * @return
	 */
	@PostMapping(path ="/insertShop", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Shop> insertShop(@RequestBody Shop s){
		logger.info("INSERT NEW SHOP");
		try {
			Shop insShop = shopService.insert(s);
			logger.info("INSERT NEW SHOP OK");
			return new ResponseEntity<Shop>(insShop,HttpStatus.CREATED);
		}catch (Exception e) {
			logger.error("ERROR: \n", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}  
	}
	
	
	/**
	 * localhost:8080/api/updateShopById/
	 * this method update one field of Shop by id_negozio
	 * @param id
	 * @param art
	 * @return
	 */
	@PutMapping("/updateShopById/{id}")
	public ResponseEntity <String> updateShopById(@PathVariable("id") Long id, @RequestBody Shop s){
		logger.info("UPDATE SHOP BY ID");
		 try { 
			 shopService.update(id, s);
			 return new ResponseEntity<String>("SHOP MODIFIED",HttpStatus.OK);
		 }catch(Exception e) {			 
			 logger.error("ERROR: \n", e);
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		 }
	}
	
	/**
	 * localhost:8080/api/deleteShopById/
	 * this method delete one field by id_negozio
	 * @param id
	 * @return
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
	 * @param 
	 * @return
	 */
	@PostMapping(path ="/insertSupplier", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Supplier> insertSupplier(@RequestBody Supplier s){
		logger.info("INSERT NEW SUPPLIER");
		try {
			Supplier insSupplier = supplierService.insert(s);
			logger.info("INSERT NEW SUPPLIER OK");
			return new ResponseEntity<Supplier>(insSupplier,HttpStatus.CREATED);
		}catch (Exception e) {
			logger.error("ERROR: \n", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}  
	}
	
	
	/**
	 * localhost:8080/api/updateSupplierById/
	 * this method update one field of Supplier by id_fornitore
	 * @param id
	 * @param art
	 * @return
	 */
	@PutMapping("/updateSupplierById/{id}")
	public ResponseEntity <String> updateSupplierById(@PathVariable("id") Long id, @RequestBody Supplier s){
		logger.info("UPDATE SUPPLIER BY ID");
		 try { 
			 supplierService.update(id, s);
			 return new ResponseEntity<String>("SUPPLIER MODIFIED",HttpStatus.OK);
		 }catch(Exception e) {			 
			 logger.error("ERROR: \n", e);
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		 }
	}
	
	/**
	 * localhost:8080/api/deleteSupplierById/
	 * this method delete one field by id_fornitore
	 * @param id
	 * @return
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
