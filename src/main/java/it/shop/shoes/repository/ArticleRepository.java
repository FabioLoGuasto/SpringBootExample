package it.shop.shoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.shop.shoes.model.Article;
import jakarta.transaction.Transactional;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long>{
	
	
	@Transactional
	@Query(value = "SELECT * FROM article WHERE negozio_id =:primoParametro AND codice =:secondoParametro ", nativeQuery = true)
	List <Article> queryRicerca (@Param("primoParametro") int negozio_id, @Param("secondoParametro") String codice);
	
	@Transactional
	@Modifying
	@Query (value = "UPDATE article SET transazione_id =:firstParam, venduto =:secondParam WHERE id_articolo =:thirdParam", nativeQuery = true)
	public void queryUpdateSellOutArticle (@Param("firstParam") Long transazione_id, @Param("secondParam") int venduto, @Param("thirdParam") Long id_articolo);
	
	
	/**
	 * Using Like: select ... like :primoParametro
	 * StartingWith: select ... like :primoParametro%
	 * EndingWith: select ... like %:primoParametro
	 * Containing: select ... like %:primoParametro%
	 */
	@Transactional
	@Query(value = "SELECT * FROM article WHERE brand LIKE %:primoParametro%", nativeQuery = true)
	List <Article> queryForBrand (@Param("primoParametro") String brand);
	
}
