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
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_univoco_negozio")
	private Long idUnivocoNegozio;
	
	@Column(nullable = true, name = "numero_negozio")
	private int shopNumber;
	
	@Column(nullable = true, name = "nome")
	private String branchName;
	
	@Column(nullable = true, name = "localita")
	private String branchLocality;


	public Shop(Long idUnivocoNegozio, int shopNumber, String branchName, String branchLocality) {
		this.idUnivocoNegozio = idUnivocoNegozio;
		this.shopNumber = shopNumber;
		this.branchName = branchName;
		this.branchLocality = branchLocality;
	}

	
}
