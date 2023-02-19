package it.shop.shoes.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.shop.shoes.dto.DtoCodeShop;
import it.shop.shoes.model.Article;
import it.shop.shoes.repository.ArticleRepository;
import it.shop.shoes.utils.ArticleUtils;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ArticleServiceImplement implements ArticleService{
	
	@Autowired private ArticleRepository articleRepository;
//	@PersistenceContext EntityManager entityManager;
	
	/**
	 * method for insert new Article.
	 */
	public Article insert(Article a) {
		Article art = new Article(a.getIdArticolo(), a.getCode(), a.getSize(), a.getNegozioId(), a.getBrand(), a.getCategory(), a.getPrice(),a.getDiscount(), a.getSeason(), a.getSellOut(),a.getSupplierId(), a.getTransactionId());
		return articleRepository.save(art);
	}
	
	
	/**
	 * this method get a list of all articles from all shops
	 */
	public List<Article> getArticles() {
		return articleRepository.findAll();
	}
	
	
	/**
	 * this method update a selected field of article by idArticolo
	 */
	public void update(Long id, Article art) {
		art.setIdArticolo(id);
		articleRepository.save(art);
	}
	
	
	/**
	 * this method delete an article by idArticolo
	 */
	public void delete(Long id) {
		articleRepository.deleteById(id);		
	}

	
	/**
	 * This method invokes the query "queryRicerca" which returns all the fields of the Article table, 
	 * which have a specific code and shopId (chosen by the user).
	 */
	@Override
	public List<DtoCodeShop> queryRicerca(int negozioId, String codice) {
		return ArticleUtils.researchForCodeShop(articleRepository.queryRicerca(negozioId, codice));
	}

	/**
	 * This method takes as input a String, which is a user entered brand.
	 * 
	 * The method invokes the "queryForBrand" query which will return all the article that have that brand entered by the user.
	 */
	@Override
	public List<Article> researchForBrand(String brand) {
		return articleRepository.queryForBrand(brand);
	}

	/**
	 * This method return one article by idArticle 
	 */
	@Override
	public Article findById(Long id) {
		return articleRepository.QueryFetch(id);
	}


//	EntityGraph graph = this.entityManager.createEntityGraph(Article.class);
//	Subgraph itemGraph = graph.addSubgraph("NegozioId");
//	Map<Long,Article> a = new HashMap<>();
	
	
}
