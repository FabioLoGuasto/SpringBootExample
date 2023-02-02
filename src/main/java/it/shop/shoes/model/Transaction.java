package it.shop.shoes.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_transazione")
	private Long idTransazione;
	
	@Column(nullable = true, name = "numero_tessera")
	private String fidelityNumber;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = FidelityClient.class, cascade = CascadeType.REFRESH)
	@JoinColumn(nullable = true, name = "cliente_id")
//	@JsonBackReference
	private FidelityClient clientId;

	@OneToMany(mappedBy = "transactionId", targetEntity = Article.class, fetch = FetchType.LAZY)
//	@JsonManagedReference
	private Set<Article> listArticleOnTransaction = new HashSet<>();	
	

	public Transaction(Long idTransazione, String fidelityNumber, FidelityClient clientId) {
		this.idTransazione = idTransazione;
		this.fidelityNumber = fidelityNumber;
		this.clientId = clientId;
	}
	
	
	

}
