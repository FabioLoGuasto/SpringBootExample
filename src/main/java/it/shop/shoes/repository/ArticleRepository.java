package it.shop.shoes.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.shop.shoes.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long>{
	
	//QUERY
	@Query(value = "SELECT * FROM article WHERE negozio_id =:primoParametro AND codice =:secondoParametro ", nativeQuery = true)
	List <Article> ricerca (@Param("primoParametro") int negozio_id, @Param("secondoParametro") String codice);
	
	
}
