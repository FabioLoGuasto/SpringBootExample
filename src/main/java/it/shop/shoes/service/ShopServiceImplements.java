package it.shop.shoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.shop.shoes.model.Shop;
import it.shop.shoes.repository.ShopRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ShopServiceImplements implements ShopService{

	@Autowired
	private ShopRepository shopRepository;
	
	/**
	 * insert new shop
	 */
	@Override
	public Shop insert(Shop s) {
		Shop shop = new Shop(s.getIdUnivocoNegozio(),s.getShopNumber(), s.getBranchName(), s.getBranchLocality());
		return shopRepository.save(shop);
	}

	/**
	 * get list of shops
	 */
	@Override
	public List<Shop> getShops() {
		return shopRepository.findAll();
	}

	/**
	 * update field of shop by id
	 */
	@Override
	public void update(Long id, Shop s) {
		s.setIdUnivocoNegozio(id);
		shopRepository.save(s);
	}

	/**
	 * delete field of shop by id
	 */
	@Override
	public void delete(Long id) {
		shopRepository.deleteById(id);
	}

}
