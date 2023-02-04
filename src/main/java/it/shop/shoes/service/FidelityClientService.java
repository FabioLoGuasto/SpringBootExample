package it.shop.shoes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.shop.shoes.model.FidelityClient;

@Service
public interface FidelityClientService {

	public FidelityClient insert(FidelityClient f); 
	public List<FidelityClient> getFidelityClients();
	public void update(Long id,FidelityClient f);
	public void delete (Long id);
}
