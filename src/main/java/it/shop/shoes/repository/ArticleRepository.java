package it.shop.shoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.shop.shoes.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long>{
	
	//QUERY
	@Query(value = "SELECT	a.codice,a.taglia, s.numero_negozio FROM article a, shop s WHERE s.id_univoco_negozio = a.negozio_id AND a.negozio_id = 3 AND a.codice LIKE 'A011'", nativeQuery = true)
	List<Article>ricerca();
	

}
