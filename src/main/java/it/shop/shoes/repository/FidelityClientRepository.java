package it.shop.shoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.shop.shoes.model.FidelityClient;

@Repository
public interface FidelityClientRepository extends JpaRepository<FidelityClient,Long>{

}
