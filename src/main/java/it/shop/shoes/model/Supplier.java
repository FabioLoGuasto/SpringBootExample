package it.shop.shoes.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Supplier {
	
	/**
	 * unique id of supplier
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_fornitore")
	private Long idFornitore; 
	
	/**
	 * code of supplier
	 */
	@Column(nullable = true, name = "cod_fornitore")
	private int supplierCode;
	
	/**
	 * name of company
	 */
	@Column(nullable = true, name = "ragione_sociale")
	private String companyName;

	/**
	 * nation where there the company
	 */
	@Column(nullable = true, name = "nazione")
	private String nation;
	
	public Supplier(Long idFornitore, int supplierCode, String companyName, String nation) {
		this.idFornitore = idFornitore;
		this.supplierCode = supplierCode;
		this.companyName = companyName;
		this.nation = nation;
	}
	

	/**
	 * supplier's list of items
	 * FetchType default is LAZY
	 */
	@OneToMany(mappedBy = "supplierId", targetEntity = Article.class,cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE,CascadeType.REMOVE}) 
	private Set <Article> listArticlesOfSupplied = new HashSet<>(); 

	
}
