package it.shop.shoes.dto; //https://www.youtube.com/watch?v=SG2gfTPzSQE

import it.shop.shoes.model.Shop;
import it.shop.shoes.model.Supplier;
import lombok.Data;

@Data
public class ArticleDto {

	private String code;
	private int size;
	private Shop negozioId;
	private String brand;
	private String category;
	private double price;
	private int discount;
	private String season;
	private Integer sellOut;
	private Supplier supplierId;
}
