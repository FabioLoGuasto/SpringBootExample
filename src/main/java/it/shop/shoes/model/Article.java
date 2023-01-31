package it.shop.shoes.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data // Getters/Setters/ToString
@Entity
@Table(name="article", schema="negozio_scarpe")
public class Article {
	
	public Article() {}
	public Article(Long idArticle, String code, int size, int negozioId, String brand, String category, double price,
			int discount, String season, int sellOut, int supplierId, int transactionId) {
		this.idArticle = idArticle;
		this.code = code;
		this.size = size;
		this.negozioId = negozioId;
		this.brand = brand;
		this.category = category;
		this.price = price;
		this.discount = discount;
		this.season = season;
		this.sellOut = sellOut;
		this.supplierId = supplierId;
		this.transactionId = transactionId;
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idArticle;
	
	@Column(nullable = true, name = "code", length=10)
	private String code;
	
	@Column(name = "size")
	private int size;
	
	@Column(nullable = true,name = "negozio_id")
	private int negozioId;
	
	@Column(nullable = true,name = "brand")
	private String brand;
	
	@Column(nullable = true,name = "category")
	private String category;
	
	@Column(nullable = true,name = "price")
	private double price;
	
	@Column(nullable = true,name = "discount")
	private int discount;
	
	@Column(nullable = true,name = "season")
	private String season;
	
	@Column(name = "sellOut")
	private int sellOut;
	
	@Column(nullable = true,name = "supplier_id")
	private int supplierId;
	
	@Column(nullable = true,name = "transaction_id")
	private int transactionId;
	
	
}
