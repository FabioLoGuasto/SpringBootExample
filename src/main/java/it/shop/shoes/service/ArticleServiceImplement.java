package it.shop.shoes.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.shop.shoes.dto.DtoBrandCode;
import it.shop.shoes.dto.DtoCodeShop;
import it.shop.shoes.model.Article;
import it.shop.shoes.repository.ArticleRepository;
import jakarta.transaction.Transactional;



@Service
@Transactional
public class ArticleServiceImplement implements ArticleService{
	
	@Autowired private ArticleRepository articleRepository;
	
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
	 * This method takes as parameter the entire list of the Article table, and returns a list with only field
	 * code and brand declared in the DtoBrandCode class.
	 */
	public List<DtoBrandCode> questCodeAndBrand(List<Article> listaArticolo) {
		List <DtoBrandCode> listaDto = new ArrayList<>();
		for (Article a : listaArticolo) {
			DtoBrandCode dto = new DtoBrandCode();
			dto.setCode(a.getCode());
			dto.setBrand(a.getBrand());
			listaDto.add(dto);
		}
		return listaDto;
	}
	
	/**
	 * This method invokes the query "queryRicerca" which returns all the fields of the Article table, 
	 * which have a specific code and shopId (chosen by the user).
	 */
	@Override
	public List<Article> queryRicerca(int negozioId, String codice) {
		return articleRepository.queryRicerca(negozioId, codice);
	}

	/**
	 * This method takes as parameter a list of articles determined by the "queryRicerca" query which returns all the fields 
	 * of the article table that have a specific code (chosen by the user) and a specific id Shop (chosen by the user).
	 * 
	 * This method instead will return specific fields that have been declared in the "DtoCodeShop" class
	 * 
	 * This is the second method of method dtoCodeShop of ControllerArticle 
	 */
	@Override
	public List<DtoCodeShop> researchForCodeShop(List<Article> listaArticolo) {
		List <DtoCodeShop> listaDto = new ArrayList<>();
		for (Article a : listaArticolo) {
			DtoCodeShop dto = new DtoCodeShop();
			dto.setCode(a.getCode());
			dto.setNumberShop(a.getNegozioId().getShopNumber());
			dto.setSize(a.getSize());
			listaDto.add(dto);
		}
		return listaDto;
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


	
}
