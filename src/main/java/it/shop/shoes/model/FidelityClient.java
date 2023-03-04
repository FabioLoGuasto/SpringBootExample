package it.shop.shoes.model;

import java.util.HashSet;
import java.util.Set;

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
@Table(name="fidelity_client", schema="negozio_scarpe")
public class FidelityClient {
	
	/**
	 * unique id of client
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_cliente")
	private Long idCliente;
	
	/**
	 * fiscal code of client
	 */
	@Column(nullable = true,name = "cf")
	private String fiscalCodeClient;
	
	/**
	 * locality where live the client
	 */
	@Column(nullable = true,name = "localita")
	private String localityClient;
	
	/**
	 * province where live the client
	 */
	@Column(nullable = true,name = "provincia")
	private String provinceClient;

	/**
	 * list of transaction
	 * there is a relation with transaction table
	 */
	@OneToMany(mappedBy = "clientId", targetEntity = Transaction.class,cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE,CascadeType.REMOVE})
	private Set <Transaction> listOfTransaction = new HashSet<>();
	

	public FidelityClient(Long idCliente, String fiscalCodeClient, String localityClient, String provinceClient) {
		this.idCliente = idCliente;
		this.fiscalCodeClient = fiscalCodeClient;
		this.localityClient = localityClient;
		this.provinceClient = provinceClient;
	}
	
	
}
