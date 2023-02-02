package it.shop.shoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.shop.shoes.model.FidelityClient;
import it.shop.shoes.repository.FidelityClientRepository;

@Service
public class FidelityClientImplements implements FidelityClientService{

	@Autowired private FidelityClientRepository fidelityClientRepository;
	
	@Override
	public FidelityClient insert(FidelityClient f) {
		FidelityClient fc = new FidelityClient(f.getIdCliente(), f.getFiscalCodeClient(), f.getLocalityClient(), f.getProvinceClient());
		return fidelityClientRepository.save(fc);
	}

	@Override
	public List<FidelityClient> getFidelityClients() {
		return fidelityClientRepository.findAll();
	}

	@Override
	public void update(Long id, FidelityClient f) {
		f.setIdCliente(id);
		fidelityClientRepository.save(f);
	}

	@Override
	public void delete(Long id) {
		fidelityClientRepository.deleteById(id);
	}

}
