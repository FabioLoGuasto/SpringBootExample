package it.shop.shoes.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import lombok.NoArgsConstructor;


@Data // Getters/Setters/ToString
@Entity
@NoArgsConstructor
@Table(name="article", schema="negozio_scarpe")
//@NamedEntityGraph(name = "graph.Article.negozioId",
//			attributeNodes = @NamedAttributeNode("negozioId"))
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Article{
	
	public Article(Long idArticolo, String code, int size, Shop negozioId, String brand, String category, double price,
			int discount, String season, int sellOut, Supplier supplierId, Transaction transactionId) {
		this.idArticolo = idArticolo;
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
	 * unique id of article
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_articolo")
	private Long idArticolo;
	
	  
	/**
	 *  code of article type
	 */
	@Column(nullable = true, name = "codice", length=10)
	private String code;
	
	/**
	 * size article
	 */
	@Column(name = "taglia")
	private int size;
	
	/**
	 * id of the shop
	 * there is relation with shop table
	 */
	@OneToOne(fetch = FetchType.LAZY,targetEntity = Shop.class) 
	@JoinColumn(nullable = true,name = "negozio_id")
	private Shop negozioId;
	
	/**
	 * brand of article
	 */
	@Column(nullable = true,name = "brand")
	private String brand;
	
	/**
	 * category of article between w (woman) - m (man) - k (kids) - s (sport)
	 */
	@Column(nullable = true,name = "categoria")
	private String category;
	
	/**
	 * price of article
	 */
	@Column(nullable = true,name = "prezzo")
	private double price;
	
	/**
	 * discount of article
	 */
	@Column(nullable = true,name = "sconto")
	private int discount;
	
	/**
	 * season of article between SS23 (spring-summer 2023) and FW22 (fall-winter 2022)
	 */
	@Column(nullable = true,name = "stagione")
	private String season;
	
	/**
	 * sell out of article 
	 * if 1, the article is in the shop
	 * if 0, the article has been sold 
	 */
	@Column(columnDefinition = "default '1'",name = "venduto")
	private Integer sellOut;
	
	/**
	 * id of supplier
	 * there is a relation with supplier table
	 */
	@ManyToOne(fetch = FetchType.LAZY,targetEntity = Supplier.class) // @ManyToOne di default è EAGER, quindi mettendolo EAGER funziona, ma se io lo voglio LAZY
	@JoinColumn(nullable = true, name = "fornitore_id") //e fare in modo di caricarlo mettendo LAZY, commentanto nella classe Supllier @jsonignore, da errore
	private Supplier supplierId;
	
	/**
	 * id of transaction
	 * there is a relation with transaction table
	 */
	@ManyToOne(fetch = FetchType.LAZY,targetEntity = Transaction.class) //fetch = FetchType.LAZY, 
	@JoinColumn(nullable = true,name = "transazione_id")
	private Transaction transactionId;
	

}
