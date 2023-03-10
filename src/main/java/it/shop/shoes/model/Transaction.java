package it.shop.shoes.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data // Getters/Setters/ToString
@Entity
@Table(name="transaction", schema="negozio_scarpe")
public class Transaction {
	
	
	/**
	 * unique id of transaction
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_transazione")
	private Long idTransazione;
	
	/**
	 * number client's card fidelity 
	 * it may be in the receipt but it may also not be there
	 */
	@Column(nullable = true, name = "numero_tessera")
	private String fidelityNumber;
	
	/**
	 * id of the client of ther's fidelity card
	 * there is relation with FidelityClient table
	 */
	@ManyToOne(targetEntity = FidelityClient.class) 
	@JoinColumn(nullable = true, name = "cliente_id")
	private FidelityClient clientId;

	/**
	 * list Article On Transaction
	 * ther's a relation with Article table
	 */
	@OneToMany(mappedBy = "transactionId", targetEntity = Article.class,cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE,CascadeType.REMOVE})
	private Set <Article> listArticleOnTransaction = new HashSet<>();	
	

	public Transaction(Long idTransazione, String fidelityNumber, FidelityClient clientId) {
		this.idTransazione = idTransazione;
		this.fidelityNumber = fidelityNumber;
		this.clientId = clientId;
	}
	
	
	

}
