package it.shop.shoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.shop.shoes.model.FidelityClient;
import it.shop.shoes.repository.FidelityClientRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional

public class FidelityClientImplements implements FidelityClientService{

	@Autowired private FidelityClientRepository fidelityClientRepository;
	
	/**
	 * insert new fidelity client
	 */
	@Override
	public FidelityClient insert(FidelityClient f) {
		FidelityClient fc = new FidelityClient(f.getIdCliente(), f.getFiscalCodeClient(), f.getLocalityClient(), f.getProvinceClient());
		return fidelityClientRepository.save(fc);
	}

	/**
	 * get list of fidelity client
	 */
	@Override
	public List<FidelityClient> getFidelityClients() {
		return fidelityClientRepository.findAll();
	}

	/**
	 * update field of fidelity client by id
	 */
	@Override
	public void update(Long id, FidelityClient f) {
		f.setIdCliente(id);
		fidelityClientRepository.save(f);
	}

	/**
	 * delete fidelity client by id
	 */
	@Override
	public void delete(Long id) {
		fidelityClientRepository.deleteById(id);
	}

}
