package it.shop.shoes.service;

import java.util.List;
import org.springframework.stereotype.Service;

import it.shop.shoes.model.Shop;

@Service
public interface ShopService {
	
	public Shop insert(Shop s); 
	public List<Shop> getShops();
	public void update(Long id,Shop s);
	public void delete (Long id);

}
