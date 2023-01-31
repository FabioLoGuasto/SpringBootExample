package it.shop.shoes.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data // Getters/Setters/ToString
@Entity
@Table(name="transaction", schema="negozio_scarpe")
public class Transaction {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_transazione;
	
	@Column(nullable = true, name = "numero_tessera")
	private String fidelityNumber;
	
	@Column(nullable = true, name = "cliente_id")
	private String clientId;

	public Transaction(Long id_transazione, String fidelityNumber, String clientId) {
		this.id_transazione = id_transazione;
		this.fidelityNumber = fidelityNumber;
		this.clientId = clientId;
	}
	
	
	

}
