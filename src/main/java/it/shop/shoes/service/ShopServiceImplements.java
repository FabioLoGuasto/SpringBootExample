package it.shop.shoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.shop.shoes.model.Shop;
import it.shop.shoes.repository.ShopRepository;

@Service
public class ShopServiceImplements implements ShopService{

	@Autowired
	private ShopRepository shopRepository;
	
	
	@Override
	public Shop insert(Shop s) {
		Shop shop = new Shop(s.getId_univoco_negozio(),s.getShopNumber(), s.getBranchName(), s.getBranchLocality());
		return shopRepository.save(shop);
	}

	@Override
	public List<Shop> getShops() {
		return shopRepository.findAll();
	}

	@Override
	public void update(Long id, Shop s) {
		s.setId_univoco_negozio(id);
		shopRepository.save(s);
	}

	@Override
	public void delete(Long id) {
		shopRepository.deleteById(id);
	}

}
