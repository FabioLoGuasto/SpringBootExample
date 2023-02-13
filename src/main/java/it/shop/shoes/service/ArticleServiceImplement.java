package it.shop.shoes.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.shop.shoes.dto.DtoBrandCode;
import it.shop.shoes.dto.RequestInsertTransazione;
import it.shop.shoes.dto.DtoCodeShop;
import it.shop.shoes.model.Article;
import it.shop.shoes.model.Transaction;
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
	 * this method convert a list of Article in one list DtoBrandCode (code and brand)
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
	 * this method return a list of article by field negozioId and codice from query
	 */
	@Override
	public List<Article> queryRicerca(int negozioId, String codice) {
		return articleRepository.queryRicerca(negozioId, codice);
	}

	/**
	 * this method take a list of article by selected field negozioId and code
	 * and than return a list with the field of DtoCodeShop (code - size - negozioId)
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


//	/**
//	 * This method insert a new transaction and update the sellOut of selected idArticolo.
//	 * This is a possibility example of sell of one article
//	 */
//	@Override
//	public void updateSellOutArticle(Long transazione_id, int venduto, Long id_articolo) {
//		articleRepository.queryUpdateSellOutArticle(transazione_id, venduto, id_articolo);
//	}


	/**
	 * This method return a list with all articles from one selected brand
	 */
	@Override
	public List<Article> researchForBrand(String brand) {
		return articleRepository.queryForBrand(brand);
	}


	
	@Override
	public RequestInsertTransazione requestInsTrans(RequestInsertTransazione r) {
		Transaction t = new Transaction();
		Article art = new Article();
		art.setIdArticolo(r.getIdArticolo());
		t.setIdTransazione(r.getObjectTransaction().getIdTransazione());
		t.setFidelityNumber(r.getObjectTransaction().getFidelityNumber());
		t.setClientId(r.getObjectTransaction().getClientId());
		return null;
	}


}
