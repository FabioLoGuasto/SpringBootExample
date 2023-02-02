package it.shop.shoes.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="fidelity_client", schema="negozio_scarpe")
public class FidelityClient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id_cliente;
	
	@Column(nullable = true,name = "cf")
	private String fiscalCodeClient;
	
	@Column(nullable = true,name = "localita")
	private String localityClient;
	
	@Column(nullable = true,name = "provincia")
	private String provinceClient;

	@OneToMany(mappedBy = "clientId", targetEntity = Transaction.class, fetch = FetchType.LAZY)
//	@JsonManagedReference
	private Set<Transaction> listOfTransaction = new HashSet<>();
	
	public FidelityClient() {}

	public FidelityClient(Long id_cliente, String fiscalCodeClient, String localityClient, String provinceClient) {
		this.id_cliente = id_cliente;
		this.fiscalCodeClient = fiscalCodeClient;
		this.localityClient = localityClient;
		this.provinceClient = provinceClient;
	}
	
	
}
