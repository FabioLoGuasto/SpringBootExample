package it.shop.shoes.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data // Getters/Setters/ToString
@Entity
@Table(name="shop", schema="negozio_scarpe")
public class Shop {
	
	/**
	 * unique id of shop
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_univoco_negozio")
	private Long idUnivocoNegozio;
	
	/**
	 * number of shop
	 */
	@Column(nullable = true, name = "numero_negozio")
	private int shopNumber;
	
	/**
	 * shop branch name
	 */
	@Column(nullable = true, name = "nome")
	private String branchName;
	
	/**
	 * locality where there the shop branch
	 */
	@Column(nullable = true, name = "localita")
	private String branchLocality;


	public Shop(Long idUnivocoNegozio, int shopNumber, String branchName, String branchLocality) {
		this.idUnivocoNegozio = idUnivocoNegozio;
		this.shopNumber = shopNumber;
		this.branchName = branchName;
		this.branchLocality = branchLocality;
	}

	
}
