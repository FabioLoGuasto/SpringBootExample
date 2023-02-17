package it.shop.shoes.service;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import it.shop.shoes.dto.DtoBrandCode;
import it.shop.shoes.model.Article;
import it.shop.shoes.dto.DtoCodeShop;



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
	 * This method takes as parameter the entire list of the Article table, and returns a list with only field
	 * code and brand declared in the DtoBrandCode class.
	 * @param article Object
	 * @return AticleDto Object
	 */
	public List<DtoBrandCode> questCodeAndBrand(List<Article> listaArticolo);
	
	/**
	 * This method invokes the query "queryRicerca" which returns all the fields of the Article table, 
	 * which have a specific code and shopId (chosen by the user).
	 * 
	 * @param negozioId : shop selected 
	 * @param codice : code selected
	 * @return list of article with negozioId and code selected
	 */
	public List <Article> queryRicerca (@Param("primoParametro") int negozioId, @Param("secondoParametro") String codice);
	
	/**
	 * This method takes as parameter a list of articles determined by the "queryRicerca" query which returns all the fields 
	 * of the article table that have a specific code (chosen by the user) and a specific id Shop (chosen by the user).
	 * 
	 * This method instead will return specific fields that have been declared in the "DtoCodeShop" class
	 * @param listArticle : list of article from queryRicerca
	 * @return list of RicercaDto
	 */
	public List<DtoCodeShop> researchForCodeShop(List<Article> listArticle);
	
	
	/**
	 * This method return all the article that have that brand entered by the user.
	 * @param brand : brand choosen by the user
	 * @return list of articles for that brand
	 */
	public List <Article> researchForBrand (@Param("primoParametro") String brand);

	
	/**
	 * This method return one article by idArticle 
	 * @param id : id of chosen article
	 * @return : one article
	 */
	public Article findById(Long id);

}
