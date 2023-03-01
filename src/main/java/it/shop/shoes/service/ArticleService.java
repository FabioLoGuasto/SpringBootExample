package it.shop.shoes.service;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import it.shop.shoes.model.Article;
import it.shop.shoes.dto.DtoArticle;
import it.shop.shoes.dto.DtoCodeShop;


@Service
public interface ArticleService {

	/**
	 * Method for insert new Article.
	 * @param article : new Article from browser
	 * @return new Article into database
	 */
	public Article insert(Article article); 
	
	/**
	 * Return all articles from Article table
	 * @return list of articles
	 */
//	public List<Article> getArticles();
	
	/**
	 * Return all articles from DtoArticle class
	 * This class have : (id - code - size - negozio - brand - category - price - discount - sellOut - supplier) 
	 * @return list of DtoArticles
	 */
	public List<DtoArticle> getDtoArticles();
	
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
	 * This method invokes the query "queryRicerca" which returns all the fields of the Article table, 
	 * which have a specific code and shopId (chosen by the user).
	 * 
	 * @param negozioId : shop selected 
	 * @param codice : code selected
	 * @return list of article with negozioId and code selected
	 */
	public List <DtoCodeShop> queryRicerca (@Param("primoParametro") int negozioId, @Param("secondoParametro") String codice);
	
	/**
	 * This method return all the article that have that brand entered by the user.
	 * @param brand : brand choosen by the user
	 * @return list of articles for that brand
	 */
	public List <Article> researchForBrand (@Param("primoParametro") String brand);
	
	/**
	 * In the example code i provided, supplierId refers to the supplier field of the Article entity and idArticle for article choosen.
	 * 
	 * The JOIN FETCH syntax in the query specifies that JPA should load the supplier entity along with the Article entity in a single query.
	 * By doing so, the supplierId field of each article returned by the query will be populated with the corresponding Supplier entity,
	 * and you can access the properties of the Supplier entity through the idSupplier field of the Article entity.
	 * 
	 * @param id of article chosen
	 * @return article with JOIN FETCH with supplierId
	 */
	public Article getOneArticleFetchSupplier(Long id);
	
	
	/**
	 * The JOIN FETCH syntax tells JPA to fetch the supplier entity with the Article entity in a single query. 
	 * Note that you need to specify the fetch attribute on the @ManyToOne and @OneToMany annotations to control 
	 * the fetch strategy for the related entities. In this example, we use FetchType.LAZY to fetch the related entities lazily, 
	 * which means that they will be loaded only when accessed. 
	 * This can help to reduce the amount of data loaded from the database and improve performance.
	 * 
	 * In the example code I provided, supplierId refers to the supplier field of the Article entity.
	 * a is an alias for the Article entity in the JPA query SELECT a FROM Article a JOIN FETCH a.supplierId, and supplierId is a field
	 * in the Article entity that is annotated with @ManyToOne to indicate a many-to-one relationship with the Customer entity.
	 * 
	 * The JOIN FETCH syntax in the query specifies that JPA should load the supplier entity along with the Article entity in a single query.
	 * By doing so, the supplierId field of each article returned by the query will be populated with the corresponding Supplier entity,
	 * and you can access the properties of the Supplier entity through the idSupplier field of the Article entity.
	 * 
	 * @return List of article with JOIN FETCH with supplierId
	 */
	public List<Article> getAllArticleFetchSupplier();

}
