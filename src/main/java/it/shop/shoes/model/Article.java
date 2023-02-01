package it.shop.shoes.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Data // Getters/Setters/ToString
@Entity
@Table(name="article", schema="negozio_scarpe")
public class Article {
	
	public Article() {}
	public Article(Long id_articolo, String code, int size, Shop negozioId, String brand, String category, double price,
			int discount, String season, int sellOut, Supplier supplierId, int transactionId) {
		this.id_articolo = id_articolo;
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
	
	/**
	 * Constructor without transaction_id
	 */
	public Article(Long id_articolo, String code, int size, Shop negozioId, String brand, String category, double price,
			int discount, String season, int sellOut, Supplier supplierId) {
		this.id_articolo = id_articolo;
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
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id_articolo;
	
	@Column(nullable = true, name = "codice", length=10)
	private String code;
	
	@Column(name = "taglia")
	private int size;
	
	@OneToOne(fetch = FetchType.EAGER, targetEntity = Shop.class, cascade = CascadeType.MERGE)
	@JoinColumn(nullable = true,name = "negozio_id")
	private Shop negozioId;
	
	@Column(nullable = true,name = "brand")
	private String brand;
	
	@Column(nullable = true,name = "categoria")
	private String category;
	
	@Column(nullable = true,name = "prezzo")
	private double price;
	
	@Column(nullable = true,name = "sconto")
	private int discount;
	
	@Column(nullable = true,name = "stagione")
	private String season;
	
	@Column(columnDefinition = "default '1'",name = "venduto")
	private Integer sellOut;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Supplier.class, cascade = CascadeType.MERGE)
	@JoinColumn(nullable = true, name = "fornitore_id")
//	@JsonBackReference
	private Supplier supplierId;
	
	@Column(nullable = true,name = "transazione_id")
	private Integer transactionId;
	

}
