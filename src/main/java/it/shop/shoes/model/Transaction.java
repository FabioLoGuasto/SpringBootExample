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
@Table(name="transaction", schema="negozio_scarpe")
public class Transaction {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idTransaction;
	
	@Column(nullable = true, name = "numero_tessera")
	private String fidelityNumber;
	
	@Column(nullable = true, name = "cliente_id")
	private String clientId;

	public Transaction(Long idTransaction, String fidelityNumber, String clientId) {
		this.idTransaction = idTransaction;
		this.fidelityNumber = fidelityNumber;
		this.clientId = clientId;
	}
	
	
	

}
