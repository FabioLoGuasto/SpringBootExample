package it.shop.shoes.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data // Getters/Setters/ToString
@Entity
@Table(name="shop", schema="negozio_scarpe")
public class Shop {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_univoco_negozio;
	
	@Column(nullable = true, name = "numero_negozio")
	private int shopNumber;
	
	@Column(nullable = true, name = "nome")
	private String branchName;
	
	@Column(nullable = true, name = "localita")
	private String branchLocality;

	public Shop() {}
	public Shop(Long id_univoco_negozio, int shopNumber, String branchName, String branchLocality) {
		this.id_univoco_negozio = id_univoco_negozio;
		this.shopNumber = shopNumber;
		this.branchName = branchName;
		this.branchLocality = branchLocality;
	}

	
}
