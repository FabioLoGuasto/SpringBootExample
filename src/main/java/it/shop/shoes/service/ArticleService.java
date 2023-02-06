package it.shop.shoes.service;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import it.shop.shoes.dto.ArticleDto;
import it.shop.shoes.dto.ArticleDtoExample;
import it.shop.shoes.dto.RicercaDto;
import it.shop.shoes.model.Article;


@Service
public interface ArticleService {

	/**
	 * Method for insert new Article.
	 * Now the field transactionId is deselected. During the insert there will be null into the database
	 * @param article : new Article from browser
	 * @return new Article into database
	 */
	public Article insert(Article article); 
	
	/**
	 * Return all articles from Article table
	 * @return list of articles
	 */
	public List<Article> getArticles();
	
	/**
	 * Update one row of article from selected id
	 * @param id : id of selected article
	 * @param article : article from browser
	 */
	public void update(Long id,Article article);
	
	/**
	 * Delete one article (one row)
	 * @param id : id of article selected
	 */
	public void delete (Long id);
	
	/**
	 * this method convert field Article in field ArticleDto
	 * @param article
	 * @return
	 */
	public ArticleDto EntityToDto(Article article);
	
	/**
	 * this method convert field ArticleDto in field Article
	 * @param articleDto
	 * @return
	 */
	public Article dtoToEntity(ArticleDto articleDto);
	

	/**
	 * This method convert field Article in field ArticleDtoExample
	 * @param article Object
	 * @return AticleDto Object
	 */
	public List<ArticleDtoExample> questCodeAndBrand(List<Article> listaArticolo);
	
	/**
	 * (value = "SELECT * FROM article WHERE negozio_id =:primoParametro AND codice =:secondoParametro ", nativeQuery = true)
	 * @param negozioId : shop selected 
	 * @param codice : code selected
	 * @return list of article with negozioId and code selected
	 */
	public List <Article> queryRicerca (@Param("primoParametro") int negozioId, @Param("secondoParametro") String codice);
	
	/**
	 * this method return a list with the selected field from RicercaDto
	 * @param listArticle : list of article from queryRicerca
	 * @return list of RicercaDto
	 */
	public List<RicercaDto> ricercaDto(List<Article> listArticle);
	
	
	
	
}
