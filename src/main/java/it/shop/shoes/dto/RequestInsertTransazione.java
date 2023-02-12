package it.shop.shoes.dto;

import it.shop.shoes.model.Transaction;
import lombok.Data;

@Data
public class RequestInsertTransazione {
	
	private Long idArticolo;
	private Transaction objectTransaction;

}
