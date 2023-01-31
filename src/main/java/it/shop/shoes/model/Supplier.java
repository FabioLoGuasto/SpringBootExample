package it.shop.shoes.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="supplier", schema="negozio_scarpe")
public class Supplier {
	
	@Id
	private Long cod_fornitore;
	
	@Column(nullable = true, name = "ragione_sociale")
	private String companyName;

	@Column(nullable = true, name = "nazione")
	private String nation;
	
	public Supplier() {}
	public Supplier(Long cod_fornitore, String companyName, String nation) {
		this.cod_fornitore = cod_fornitore;
		this.companyName = companyName;
		this.nation = nation;
	}
	
	
}
