package it.shop.shoes.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data // Getters/Setters/ToString
@Entity
@Table(name="shop", schema="negozio_scarpe")
public class Shop {
	
	@Id
	private Long id_negozio;
	
	@Column(nullable = true, name = "nome")
	private String branchName;
	
	@Column(nullable = true, name = "localita")
	private String branchLocality;

	public Shop() {}
	public Shop(Long id_negozio, String branchName, String branchLocality) {
		this.id_negozio = id_negozio;
		this.branchName = branchName;
		this.branchLocality = branchLocality;
	}

	
}
