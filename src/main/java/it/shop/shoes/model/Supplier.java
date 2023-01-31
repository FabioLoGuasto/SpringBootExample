package it.shop.shoes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="supplier", schema="negozio_scarpe")
public class Supplier {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codSupplier;
	
	@Column(nullable = true, name = "ragione_sociale")
	private String companyName;

	@Column(nullable = true, name = "nazione")
	private String nation;
	
	public Supplier() {}
	public Supplier(Long codSupplier, String companyName, String nation) {
		this.codSupplier = codSupplier;
		this.companyName = companyName;
		this.nation = nation;
	}
	
	
}
