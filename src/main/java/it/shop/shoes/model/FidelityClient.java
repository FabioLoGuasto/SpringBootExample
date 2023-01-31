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
@Table(name="fidelity_client", schema="negozio_scarpe")
public class FidelityClient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idClient;
	
	@Column(nullable = true,name = "cf")
	private int fiscalCodeClient;
	
	@Column(nullable = true,name = "localita")
	private int localityClient;
	
	@Column(nullable = true,name = "provincia")
	private int provinceClient;

	public FidelityClient() {}

	public FidelityClient(Long idClient, int fiscalCodeClient, int localityClient, int provinceClient) {
		this.idClient = idClient;
		this.fiscalCodeClient = fiscalCodeClient;
		this.localityClient = localityClient;
		this.provinceClient = provinceClient;
	}
	
	
}
