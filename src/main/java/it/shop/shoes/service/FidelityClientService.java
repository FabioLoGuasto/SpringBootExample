package it.shop.shoes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.shop.shoes.model.FidelityClient;

@Service
public interface FidelityClientService {

	/**
	 * Insert new client
	 * @param fidelityClient
	 * @return new client
	 */
	public FidelityClient insert(FidelityClient fidelityClient); 
	
	/**
	 * get list of client
	 * @return list
	 */
	public List<FidelityClient> getFidelityClients();
	
	/**
	 * update one client
	 * @param id : id of client
	 * @param fidelityClient
	 */
	public void update(Long id,FidelityClient fidelityClient);
	
	/**
	 * delete one client
	 * @param id : id of client
	 */
	public void delete (Long id);
}
