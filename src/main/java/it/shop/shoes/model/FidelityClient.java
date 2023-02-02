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
@Table(name="fidelity_client", schema="negozio_scarpe")
public class FidelityClient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_cliente")
	private Long idCliente;
	
	@Column(nullable = true,name = "cf")
	private String fiscalCodeClient;
	
	@Column(nullable = true,name = "localita")
	private String localityClient;
	
	@Column(nullable = true,name = "provincia")
	private String provinceClient;

	@OneToMany(mappedBy = "clientId", targetEntity = Transaction.class, fetch = FetchType.LAZY)
//	@JsonManagedReference
	private Set<Transaction> listOfTransaction = new HashSet<>();
	

	public FidelityClient(Long idCliente, String fiscalCodeClient, String localityClient, String provinceClient) {
		this.idCliente = idCliente;
		this.fiscalCodeClient = fiscalCodeClient;
		this.localityClient = localityClient;
		this.provinceClient = provinceClient;
	}
	
	
}
