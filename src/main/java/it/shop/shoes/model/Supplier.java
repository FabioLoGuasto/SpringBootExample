package it.shop.shoes.model;

import java.util.HashSet;
import java.util.Set;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name="supplier", schema="negozio_scarpe")
public class Supplier {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_fornitore")
	private Long idFornitore; 
	
	@Column(nullable = true, name = "cod_fornitore")
	private int supplierCode;
	
	@Column(nullable = true, name = "ragione_sociale")
	private String companyName;

	@Column(nullable = true, name = "nazione")
	private String nation;
	
	public Supplier(Long idFornitore, int supplierCode, String companyName, String nation) {
		this.idFornitore = idFornitore;
		this.supplierCode = supplierCode;
		this.companyName = companyName;
		this.nation = nation;
	}
	

//  mettere qua fornitore_id è errato perchè è il nome della colonna. supplierId è il nome dell'attributo nella classe Article
	@OneToMany(mappedBy = "supplierId", targetEntity = Article.class , fetch = FetchType.LAZY)
//	@JsonManagedReference
	private Set <Article> listArticlesOfSupplied = new HashSet<>(); // set non ha duplicati

	
}
