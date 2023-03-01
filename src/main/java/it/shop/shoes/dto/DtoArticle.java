package it.shop.shoes.dto;

import lombok.Data;

@Data
public class DtoArticle {
	
	private Long id;
	private String code;
	private int size;
	private String negozio;
	private String brand;
	private String category;
	private double price;
	private int discount;
	private Integer sellOut;
	private String supplier;
	
	
	
}
