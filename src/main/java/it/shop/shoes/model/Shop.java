package it.shop.shoes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data // Getters/Setters/ToString
@Entity
@Table(name="shop", schema="negozio_scarpe")
public class Shop {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idShop;
	
	@Column(nullable = true, name = "nome")
	private String branchName;
	
	@Column(nullable = true, name = "localita")
	private String branchLocality;

	public Shop() {}
	public Shop(Long idShop, String branchName, String branchLocality) {
		this.idShop = idShop;
		this.branchName = branchName;
		this.branchLocality = branchLocality;
	}

	
}
