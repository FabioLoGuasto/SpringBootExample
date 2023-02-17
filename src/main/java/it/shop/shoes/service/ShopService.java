package it.shop.shoes.service;

import java.util.List;
import org.springframework.stereotype.Service;

import it.shop.shoes.model.Shop;

@Service
public interface ShopService {
	
	/**
	 * insert new shop
	 * @param shop
	 * @return new shop
	 */
	public Shop insert(Shop shop); 
	
	/**
	 * get list of shop
	 * @return
	 */
	public List<Shop> getShops();
	
	/**
	 * update one field of shop by id
	 * @param id : id of the shop
	 * @param shop
	 */
	public void update(Long id,Shop shop);
	
	/**
	 * delete one shop by id
	 * @param id : id of the shop
	 */
	public void delete (Long id);

}
