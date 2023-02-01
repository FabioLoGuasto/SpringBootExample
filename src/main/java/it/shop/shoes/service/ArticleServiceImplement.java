package it.shop.shoes.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.shop.shoes.dto.ArticleDto;
import it.shop.shoes.model.Article;
import it.shop.shoes.repository.ArticleRepository;

@Service
public class ArticleServiceImplement implements ArticleService{

	@Autowired
	private ArticleRepository articleRepository;

	/**
	 * method for insert new Article.
	 * Now the field transaction_id is deselected. During the insert there will be null into the database
	 */
	@Override
	public Article insert(Article a) {
//		Article art = new Article(a.getId_articolo(), a.getCode(), a.getSize(), a.getNegozioId(), a.getBrand(), a.getCategory(), a.getPrice(),a.getDiscount(), a.getSeason(), a.getSellOut(),a.getSupplierId(), a.getTransactionId()); // completo
		Article art = new Article(a.getId_articolo(), a.getCode(), a.getSize(), a.getNegozioId(), a.getBrand(), a.getCategory(), a.getPrice(),a.getDiscount(), a.getSeason(), a.getSellOut(),a.getSupplierId()); 
		return articleRepository.save(art);
	}
	
	
	/**
	 * method for get the lisf of field ArticleDto
	 */
	@Override
	public List<ArticleDto> getAllArticleDto(List<Article>art){ // -------->>>>> ???????????????
		return articleRepository.findAll()
				.stream()
				.map(this::EntityToDto)
				.collect(Collectors.toList());
	}

	
	/**
	 * this method get a list of all articles from all shop
	 */
	@Override
	public List<Article> getArticles() {
		return articleRepository.findAll();
	}

	
	/**
	 * this method update a selected field of article by id_articolo
	 */
	@Override
	public void update(Long id, Article art) {
		art.setId_articolo(id);
		articleRepository.save(art);
	}
	
	
	/**
	 * DA FARE ---------------------------------------------------------------------------------------------------------------
	 */
	@Override
	public void updateFromCode(Long id, Article a) {
		// TODO Auto-generated method stub
		// VORREI MODIFICARLI X CODICE, IN MODO DA MODIFICARLI TUTTI INSIEME
		// POTREI FARE UNA SELECT PER CODICE
		// POI TRAMITE ITERAZIONE POTREI MODIFICARLI UNO PER UNO DANDO UN INPUT UGUALE PER TUTTI
	}

	
	/**
	 * this method delete an article by id_articolo
	 */
	@Override
	public void delete(Long id) {
		articleRepository.deleteById(id);		
	}
	
	
	/**
	 * this method convert fiel Article in field ArticleDto
	 */
	@Override
	public ArticleDto EntityToDto(Article art) {
		ArticleDto dto = new ArticleDto();
		dto.setCode(art.getCode());
		dto.setSize(art.getSize());
		dto.setNegozioId(art.getNegozioId());
		dto.setBrand(art.getBrand());
		dto.setCategory(art.getCategory());
		dto.setPrice(art.getPrice());
		dto.setDiscount(art.getDiscount());
		dto.setSeason(art.getSeason());
		dto.setSellOut(art.getSellOut());
		dto.setSupplierId(art.getSupplierId());
		return dto;
	}
	
	
	/**
	 * this method convert fiel ArticleDto in field Article
	 */
	@Override
	public Article dtoToEntity(ArticleDto dto) {
		Article art = new Article();
		art.setCode(dto.getCode());
		art.setSize(dto.getSize());
		art.setNegozioId(dto.getNegozioId());
		art.setBrand(dto.getBrand());
		art.setCategory(dto.getCategory());
		art.setPrice(dto.getPrice());
		art.setDiscount(dto.getDiscount());
		art.setSeason(dto.getSeason());
		art.setSellOut(dto.getSellOut());
		art.setSupplierId(dto.getSupplierId());
		return art;
	}
}
