package it.shop.shoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.shop.shoes.dto.DtoArticle;
import it.shop.shoes.dto.DtoCodeShop;
import it.shop.shoes.model.Article;
import it.shop.shoes.repository.ArticleRepository;
import it.shop.shoes.utils.ArticleUtils;
import jakarta.transaction.Transactional;


@Service
public class ArticleServiceImplement implements ArticleService{
	
	@Autowired private ArticleRepository articleRepository;

	
	/**
	 * method for insert new Article.
	 */
	@Transactional
	public Article insert(Article a) {
		Article art = new Article(a.getIdArticolo(), a.getCode(), a.getSize(), a.getNegozioId(), a.getBrand(), a.getCategory(), a.getPrice(),a.getDiscount(), a.getSeason(), a.getSellOut(),a.getSupplierId(), a.getTransactionId());
		return articleRepository.save(art);
	}
	
	
	/**
	 * this method get a list of all articles from all shops
	 */
	@Transactional
	public List<Article> getArticles() {
		return articleRepository.findAll();
	}
	
	
	/**
	 * Return all articles from DtoArticle class
	 * This class have : (id - code - size - negozio - brand - category - price - discount - sellOut - supplier) 
	 */
	@Transactional
	@Override
	public List<DtoArticle> getDtoArticles() {
		return ArticleUtils.dtoArticleMapper(articleRepository.findAll());
	}
	
	
	/**
	 * this method update a selected field of article by idArticolo
	 */
	@Transactional
	public void update(Long id, Article art) {
		art.setIdArticolo(id);
		articleRepository.save(art);
	}
	
	
	/**
	 * this method delete an article by idArticolo
	 */
	@Transactional
	public void delete(Long id) {
		articleRepository.deleteById(id);		
	}

	
	/**
	 * This method invokes the query "queryRicerca" which returns all the fields of the Article table, 
	 * which have a specific code and shopId (chosen by the user).
	 */
	@Override
	@Transactional
	public List<DtoCodeShop> queryRicerca(int negozioId, String codice) {
		return ArticleUtils.researchForCodeShopMapper(articleRepository.queryRicerca(negozioId, codice));
	}
	

	/**
	 * This method takes as input a String, which is a user entered brand.
	 * 
	 * The method invokes the "queryForBrand" query which will return all the article that have that brand entered by the user.
	 */
	@Override
	@Transactional
	public List<Article> researchForBrand(String brand) {
		return articleRepository.queryForBrand(brand);
	}


	/**
	 * In the example code i provided, supplierId refers to the supplier field of the Article entity and idArticle for article chosen.
	 * 
	 * The JOIN FETCH syntax in the query specifies that JPA should load the supplier entity along with the Article entity in a single query.
	 * By doing so, the supplierId field of each article returned by the query will be populated with the corresponding Supplier entity,
	 * and you can access the properties of the Supplier entity through the idSupplier field of the Article entity.
	 * 
	 * @param id of article chosen
	 * @return article with JOIN FETCH with supplierId
	 */
	@Override
	@Transactional
	public Article getOneArticleFetchSupplier(Long id) {
		Article articolo = articleRepository.queryGetOneArticleFetchSupplier(id);
		return articolo;
		
	}


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
	@Override
	@Transactional
	public List<Article> getAllArticleFetchSupplier() {
		return articleRepository.queryGetAllArticleFetchSupplier();
	}

}
