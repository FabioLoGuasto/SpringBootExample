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

import it.shop.shoes.dto.ArticleDto;
import it.shop.shoes.dto.DtoBrandCode;
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
	 * localhost:8080/api/article/dtoCodeShop?primoParametro=2&secondoParametro=A008
	 * @param negozioId : chosen field negozioId 
	 * @param codice : chosen field codice
	 * @return listDto :this method return a list with the selected field from dtoCodeShop
	 */
	@GetMapping(path = "/dtoCodeShop", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<DtoCodeShop>> dtoCodeShop (@RequestParam(value = "primoParametro") int negozioId, @RequestParam(value = "secondoParametro") String codice){
		logger.info("RICERCA DTO");
		try {
			List<Article> listArticle = articleService.queryRicerca(negozioId, codice);
			List<DtoCodeShop> listDto = articleService.researchForCodeShop(listArticle);
			return new ResponseEntity<List<DtoCodeShop>>(listDto, HttpStatus.OK);
		}catch(Exception e) {
			logger.error("ERROR: \n", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	/**
	 * https://www.youtube.com/watch?v=SG2gfTPzSQE
	 * localhost:8080/api/article/getAllArticlesBrandCode
	 * This method return a list DtoBrandCode (only code & brand of Article)
	 * @return listArticlesDto 
	 */
	@GetMapping(path ="/getAllArticlesBrandCode", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<DtoBrandCode>> getDtoBrandCode(){
		logger.info("GET ALL ARTICLES WITH ONLY FIELD BRAND & CODE");
		try {
			List<Article> listArticles = this.articleService.getArticles();
			List<DtoBrandCode> listArticlesDto = articleService.questCodeAndBrand(listArticles);
			return new ResponseEntity <List<DtoBrandCode>> (listArticlesDto,HttpStatus.OK);
		}catch(Exception e) {
			logger.error("ERROR " + e);
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
	 * https://www.youtube.com/watch?v=SG2gfTPzSQE
	 * localhost:8080/api/article/insertArticleDto
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
		
// ----------------------------------------------------------------------------------------------- METODI VARI
	
	/**
	 * le cose le devo passare dall'url
	 * id articolo devo passarla dall'url quindi dal paylod
	 * devo fare una classe apposta che mi mappa quel payload
	 * creare classe con dentro quel tipo di paylod. una sorta di DTO
	 * poi mi ricavo tutti gli oggetti da passare
	 * potrei avere una classe requestInsertTransazione che mi mappa il payload xkè puo capire che abbia delle cose specifiche.
	 * una classe che ha dentro id_articolo e un oggetto trasazione ed avranno get/set
	 * 
	 * payload co postman
	 * {
	 * 		id_articolo:
	 * 		oggetto transazione { id_transazione
	 * 								numero essera
	 * 							}
	 * }
	 * per fare cio devo fare una classe java che dentro dovrà avere una variabile lond id articolo e un oggetto di tipo transazione che
	 * è il payload del mio servizio
	 * 
	 * sarò io che andrò a prendere i parametri giusti dal payload
	 * nel metodo nel mio service come parametro di ingresso avrò  variabile requestInsertTransazione (che è la variabile dela classe che mi creo)
	 * 
	 * quando ho bisogno della transazione andro a fare get della transazione
	 * quando ho bisogno dell'articlo faro get dell'articolo 
	 * 
	 */
	
//	/**
//	 * localhost:8080/api/insertTransazioneUpdatevenduto
//	 * This method insert a new transaction and update the sellOut of selected idArticolo.
//	 * This is a possibiliy example of sull of one article
//	 * @param transaction : Transaction Object
//	 * @return : new trasaction and update field sellOut of Article of selected idArticle
//	 */
//	@PostMapping(path = "/insertTransazioneUpdatevenduto", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity <Transaction> insertTransazioneUpdatevenduto(@RequestBody Transaction transaction){
//		Long idTransaction = (long) 0;
//		Transaction insertTransaction;
//		Scanner s = new Scanner(System.in);
//		
//		try {
//			insertTransaction = transactionService.insert(transaction);
//			idTransaction = insertTransaction.getIdTransazione();
//			logger.info("INSERT A NEW TRANSACTION OK !!");
//		}catch(Exception e) {
//			  logger.error("ERROR: \n", e);
//			  s.close();
//			  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//		}
//		
//		try {
//			logger.info("UPDATE SELL OUT ARTICLE");
//			System.out.println("INSERISCI L'ID DELL'ARTICOLO VENDUTO");
//			Long idArticolo = s.nextLong();
//			articleService.updateSellOutArticle(idTransaction, 0, idArticolo);
//			logger.info("ARTICOLO VENDUTO, TRANSAZIONE TERMINATA !!");
//			s.close();
//			return new ResponseEntity <Transaction>(insertTransaction,HttpStatus.OK);
//		}catch(Exception e) {
//			  logger.error("ERROR: \n", e);
//			  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//		}
//		
//		
//	}
	
	
	
	
	
}
