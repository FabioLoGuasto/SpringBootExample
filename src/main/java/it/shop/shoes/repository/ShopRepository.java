package it.shop.shoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.shop.shoes.model.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop,Long>{

}
