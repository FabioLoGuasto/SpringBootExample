package it.shop.shoes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.shop.shoes.dto.ArticleDto;
import it.shop.shoes.dto.ArticleDtoExample;
import it.shop.shoes.dto.RicercaDto;
import it.shop.shoes.model.Article;
import it.shop.shoes.repository.ArticleRepository;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class ArticleServiceImplement implements ArticleService{

	@Autowired
	private ArticleRepository articleRepository;

	/**
	 * method for insert new Article.
	 * Now the field transaction_id is deselected. During the insert there will be null into the database
	 */
	public Article insert(Article a) {
		Article art = new Article(a.getIdArticolo(), a.getCode(), a.getSize(), a.getNegozioId(), a.getBrand(), a.getCategory(), a.getPrice(),a.getDiscount(), a.getSeason(), a.getSellOut(),a.getSupplierId()); 
		return articleRepository.save(art);
	}
	
	
//	/**
//	 * method for get the list of field ArticleDto
//	 */
//	public List<ArticleDto> getAllArticleDto(List<Article>art){ // -------->>>>> ???????????????
//		return articleRepository.findAll()
//				.stream()
//				.map(this::EntityToDto)
//				.collect(Collectors.toList());
//	}
	
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
	 * this method convert field Article in field ArticleDto
	 */
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
	 * this method convert field Article in field ArticleDtoExample
	 */
	public List<ArticleDtoExample> questCodeAndBrand(List<Article> listaArticolo) {
		List <ArticleDtoExample> listaDtoExample = new ArrayList<>();
		for (Article a : listaArticolo) {
			ArticleDtoExample dto = new ArticleDtoExample();
			dto.setCode(a.getCode());
			dto.setBrand(a.getBrand());
			listaDtoExample.add(dto);
		}
		return listaDtoExample;
	}
	
	/**
	 * this method convert field ArticleDto in field Article
	 */
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

	/**
	 * this method return a list with the selected field query
	 */
	@Override
	public List<Article> ricerca(int negozioId, String codice) {
		return articleRepository.queryRicerca(negozioId, codice);
	}

	/**
	 * this method return a list with the selected field from RicercaDto
	 */
	@Override
	public List<RicercaDto> ricercaDto(List<Article> listaArticolo) {
		List <RicercaDto> listaDto = new ArrayList<>();
		for (Article a : listaArticolo) {
			RicercaDto dto = new RicercaDto();
			dto.setCode(a.getCode());
			dto.setNumberShop(a.getNegozioId().getShopNumber());
			dto.setSize(a.getSize());
			listaDto.add(dto);
		}
		return listaDto;
	}


	/**
	 * This method insert a new transaction and update the sellOut of selected idArticolo.
	 * This is a possibiliy example of sull of one article
	 */
	@Override
	public void updateSellOutArticle(Long transazione_id, int venduto, Long id_articolo) {
		articleRepository.queryUpdateSellOutArticle(transazione_id, venduto, id_articolo);
	}


	/**
	 * 
	 */
	@Override
	public List<Article> researchForBrand(String brand) {
		return articleRepository.queryForBrand(brand);
	}
	
	
	
	
	
	
	
	
}
