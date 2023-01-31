package it.shop.shoes.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.shop.shoes.dto.ArticleDto;
import it.shop.shoes.model.Article;
import it.shop.shoes.service.ArticleService;

/**
 * @author fabio
 * Class used for map API endPoint
 */
@RestController
@RequestMapping(value = "/api")
public class ControllerApiRest {
	
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
	 * This method return a list with all articles from all shops
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
}
