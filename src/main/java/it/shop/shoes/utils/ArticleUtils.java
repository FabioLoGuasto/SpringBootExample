package it.shop.shoes.utils;

import java.util.ArrayList;
import java.util.List;

import it.shop.shoes.dto.DtoArticle;
import it.shop.shoes.dto.DtoCodeShop;
import it.shop.shoes.model.Article;


public class ArticleUtils {
	
	/**
	 * This method takes as parameter a list of articles determined by the "queryRicerca" query which returns all the fields 
	 * of the article table that have a specific code (chosen by the user) and a specific id Shop (chosen by the user).
	 * 
	 * This method instead will return specific fields that have been declared in the "DtoCodeShop" class
	 * 
	 * This is the second method of method dtoCodeShop of ControllerArticle 
	 */
	
//	public static List<DtoCodeShop> researchForCodeShopMapper(List<Article> listArticles) {
//		List <DtoCodeShop> listDto = new ArrayList<>();
//		for (Article article : listArticles) {
//			DtoCodeShop dto = new DtoCodeShop();
//			dto.setCode(article.getCode());
//			dto.setNumberShop(article.getNegozioId().getShopNumber());
//			dto.setSize(article.getSize());
//			listDto.add(dto);
//		}
//		return listDto;
//	}
	
	public static List<DtoCodeShop> researchForCodeShopMapper(List<Article> listArticles) {
	    return listArticles.stream()
	        .map(article -> {
	            DtoCodeShop dto = new DtoCodeShop();
	            dto.setCode(article.getCode());
	            dto.setNumberShop(article.getNegozioId().getShopNumber());
	            dto.setSize(article.getSize());
	            return dto;
	        }).toList();
	}
	
	
	
	
	
	
	
	/**
	 * This method take as parameter a list of article and return a list of DtoArticle 
	 * @param list
	 * @return
	 */
//	public static List<DtoArticle> dtoArticleMapper (List <Article> listArticle) {
//		List<DtoArticle> listDto = new ArrayList<>();
//		for(Article article : listArticle) {
//			DtoArticle dto = new DtoArticle();
//			dto.setId(article.getIdArticolo());
//			dto.setCode(article.getCode());
//			dto.setSize(article.getSize());
//			dto.setNegozio(article.getNegozioId().getBranchName());  
//			dto.setBrand(article.getBrand());
//			dto.setCategory(article.getCategory());
//			dto.setPrice(article.getPrice());
//			dto.setDiscount(article.getDiscount());
//			dto.setSellOut(article.getSellOut());
//			dto.setSupplier(article.getSupplierId().getCompanyName()); 
//			listDto.add(dto);
//		}
//		return listDto;
//	}
	
	public static List<DtoArticle> dtoArticleMapper (List <Article> listArticle) {
		return listArticle.stream()
				.map(article -> {
					DtoArticle dto = new DtoArticle();
					dto.setId(article.getIdArticolo());
					dto.setCode(article.getCode());
					dto.setSize(article.getSize());
					dto.setNegozio(article.getNegozioId().getBranchName());  
					dto.setBrand(article.getBrand());
					dto.setCategory(article.getCategory());
					dto.setPrice(article.getPrice());
					dto.setDiscount(article.getDiscount());
					dto.setSellOut(article.getSellOut());
					dto.setSupplier(article.getSupplierId().getCompanyName()); 
					return dto;
				}).toList();
	}
	

}
