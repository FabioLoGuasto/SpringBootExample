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
@Table(name="fidelity_client", schema="negozio_scarpe")
public class FidelityClient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_cliente;
	
	@Column(nullable = true,name = "cf")
	private int fiscalCodeClient;
	
	@Column(nullable = true,name = "localita")
	private int localityClient;
	
	@Column(nullable = true,name = "provincia")
	private int provinceClient;

	public FidelityClient() {}

	public FidelityClient(Long id_cliente, int fiscalCodeClient, int localityClient, int provinceClient) {
		this.id_cliente = id_cliente;
		this.fiscalCodeClient = fiscalCodeClient;
		this.localityClient = localityClient;
		this.provinceClient = provinceClient;
	}
	
	
}
