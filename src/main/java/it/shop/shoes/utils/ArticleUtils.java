package it.shop.shoes.utils;

import java.util.ArrayList;
import java.util.List;

import it.shop.shoes.dto.DtoBrandCode;
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
	
	public static List<DtoCodeShop> researchForCodeShop(List<Article> listaArticolo) {
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
	 * This method takes as parameter the entire list of the Article table, and returns a list with only field
	 * code and brand declared in the DtoBrandCode class.
	 */
	public static List<DtoBrandCode> questCodeAndBrand(List<Article> listaArticolo) {
		List <DtoBrandCode> listaDto = new ArrayList<>();
		for (Article a : listaArticolo) {
			DtoBrandCode dto = new DtoBrandCode();
			dto.setCode(a.getCode());
			dto.setBrand(a.getBrand());
			listaDto.add(dto);
		}
		return listaDto;
	} 

}
