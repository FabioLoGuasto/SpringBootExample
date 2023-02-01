package it.shop.shoes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="supplier", schema="negozio_scarpe")
public class Supplier {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_fornitore; 
	
	@Column(nullable = true, name = "cod_fornitore")
	private int supplierCode;
	
	@Column(nullable = true, name = "ragione_sociale")
	private String companyName;

	@Column(nullable = true, name = "nazione")
	private String nation;
	
	public Supplier() {}
	public Supplier(Long id_fornitore, int supplierCode, String companyName, String nation) {
		this.id_fornitore = id_fornitore;
		this.supplierCode = supplierCode;
		this.companyName = companyName;
		this.nation = nation;
	}
	
	
}
