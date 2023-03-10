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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.shop.shoes.dto.DtoArticle;
import it.shop.shoes.dto.DtoCodeShop;
import it.shop.shoes.model.Article;
import it.shop.shoes.service.ArticleService;


/**
 * @author fabio
 * Class used for map API endPoint
 */
@RestController
@RequestMapping(value = "/api/article")
public class ControllerArticle {
		
	public static final Logger logger = LoggerFactory.getLogger(ControllerArticle.class);
	
	@Autowired ArticleService articleService;

	
	/**
	 * localhost:8080/api/article/researchForBrand?primoParametro=ADIDAS
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
	 * localhost:8080/api/article/dtoCodeShop?negozioId=2&codice=A008
	 * @param negozioId : chosen field negozioId 
	 * @param codice : chosen field codice
	 * @return listDto :this method return a list with the selected field from dtoCodeShop
	 */
	@GetMapping(path = "/dtoCodeShop", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<DtoCodeShop>> dtoCodeShop (@RequestParam(value = "negozioId") int negozioId, @RequestParam(value = "codice") String codice){
		logger.info("RICERCA DTO");
		try {
			List<DtoCodeShop> listDto = articleService.queryRicerca(negozioId, codice);
			return new ResponseEntity<List<DtoCodeShop>>(listDto, HttpStatus.OK);
		}catch(Exception e) {
			logger.error("ERROR: \n", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	/**
	 * localhost:8080/api/article/getAllArticles
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
	 * localhost:8080/api/article/getDtoArticles
	 * This method return a list of DtoArticle
	 * @return listDtoArticles : List of DtoArticle class
	 */
	@GetMapping(path ="/getDtoArticles", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<DtoArticle>> getDtoArticles(){
		logger.info("GET ALL ARTICLES DTO");
		try {
			List<DtoArticle> listDtoArticles = this.articleService.getDtoArticles();
			return new ResponseEntity <List<DtoArticle>> (listDtoArticles,HttpStatus.OK);
		}catch(Exception e) {
			logger.error("ERROR " + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	/**
	 * localhost:8080/api/article/getAllArticleFetchSupplier
	 * This method return a list with all articles by JOIN FETCH with supplierId
	 * @return listArticles
	 */
	@GetMapping(path ="/getAllArticleFetchSupplier", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<Article>> getAllArticleFetchSupplier(){
		logger.info("GET ALL ARTICLES FETCH");
		try {
			List<Article> listArticles = this.articleService.getAllArticleFetchSupplier();
			return new ResponseEntity <List<Article>> (listArticles,HttpStatus.OK);
		}catch(Exception e) {
			logger.error("ERROR " + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
//	/**
//	 * localhost:8080/api/article/getOneArticleFetchSupplier/{id}
//	 * This method return one articles by JOIN FETCH with supplierId
//	 * @param id
//	 * @return
//	 */
//	@GetMapping(path = "/getOneArticleFetchSupplier/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity <Article> getOneArticleFetchSupplier(@PathVariable("id") Long id){
//		logger.info("GET ARTICLE BY ID WITH JOIN FETCH");
//		 try { 
//			 Article article = articleService.getOneArticleFetchSupplier(id);
//			 return new ResponseEntity<Article>(article,HttpStatus.OK);
//		 }catch(Exception e) {			 
//			 logger.error("ERROR: \n", e);
//			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//		 }
//	}
	
	
	/**
	 * localhost:8080/api/article/insertArticle
	 * This is the method for insert new Article.
	 * @param dto : Object ArticleDto
	 * @return newArticle : return new Article
	 */
	@PostMapping(path ="/insertArticle", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Article> insertArticle(@RequestBody Article article){
		logger.info("INSERT ARTICLE");
		try {
			Article newArticle = articleService.insert(article);
			logger.info("INSERT OK");
			return new ResponseEntity<Article>(newArticle,HttpStatus.CREATED);
		}catch (Exception e) {
			logger.error("ERROR: \n", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}  
	}
	


	/**
	 * localhost:8080/api/article/updateArticleById/
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
	 * localhost:8080/api/article/deleteArticleById/
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

	
	
}
