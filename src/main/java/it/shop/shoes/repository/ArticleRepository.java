package it.shop.shoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.shop.shoes.model.Article;
import jakarta.transaction.Transactional;


@Repository
public interface ArticleRepository extends JpaRepository<Article,Long>{
	

	@Transactional
	@Query(value = "SELECT * FROM article WHERE negozio_id =:primoParametro AND codice =:secondoParametro ", nativeQuery = true)
	public List <Article> queryRicerca (@Param("primoParametro") int negozio_id, @Param("secondoParametro") String codice);
	
	
	/**
	 * Using Like: select ... like :primoParametro
	 * StartingWith: select ... like :primoParametro%
	 * EndingWith: select ... like %:primoParametro
	 * Containing: select ... like %:primoParametro%
	 */
	@Transactional
	@Query(value = "SELECT * FROM article WHERE brand LIKE %:primoParametro%", nativeQuery = true)
	public List <Article> queryForBrand (@Param("primoParametro") String brand);
	
	
	/**
	 * In the example code i provided, supplierId refers to the supplier field of the Article entity and idArticle for article chosen.
	 * 
	 * The JOIN FETCH syntax in the query specifies that JPA should load the supplier entity along with the Article entity in a single query.
	 * By doing so, the supplierId field of each article returned by the query will be populated with the corresponding Supplier entity,
	 * and you can access the properties of the Supplier entity through the idSupplier field of the Article entity.
	 * 
	 * @param id of article chosen
	 * @return article with JOIN FETCH with supplierId
	 */
//	@Transactional
//	@Query("SELECT a FROM Article a JOIN FETCH a.supplierId WHERE a.idArticolo =:id")
//    public Article queryGetOneArticleFetchSupplier(@Param("id") Long id);
	
	
	/**
	 * The LEFT JOIN FETCH syntax tells JPA to fetch the supplier entity with the Article entity in a single query.
	 * The LEFT JOIN FETCH syntax tells JPA to fetch the transaction entity with the Article entity in a single query.
	 * The LEFT JOIN FETCH syntax tells JPA to fetch the shop entity with the Article entity in a single query. 
	 * 
	 * Note that you need to specify the fetch attribute on the @ManyToOne and @OneToMany annotations to control 
	 * the fetch strategy for the related entities. In this example, we use FetchType.LAZY to fetch the related entities lazily, 
	 * which means that they will be loaded only when accessed. 
	 * This can help to reduce the amount of data loaded from the database and improve performance.
	 * 
	 * In the example code i provided, supplierId refers to the supplier field of the Article entity.
	 * In the example code i provided, transactionId refers to the transaction field of the Article entity.
	 * In the example code i provided, negozioId refers to the shop field of the Article entity.
	 * 
	 * a is an alias for the Article entity in the JPA query SELECT a FROM Article a LEFT JOIN FETCH a.supplierId, and supplierId is a field
	 * in the Article entity that is annotated with @ManyToOne to indicate a many-to-one relationship with the Customer entity.
	 * 
	 * The LEFT JOIN FETCH syntax in the query specifies that JPA should load the supplier entity along with the Article entity in a single query.
	 * By doing so, the supplierId field of each article returned by the query will be populated with the corresponding Supplier entity,
	 * and you can access the properties of the Supplier entity through the idSupplier field of the Article entity.
	 * 
	 * The LEFT JOIN FETCH syntax in the query specifies that JPA should load the transaction entity along with the Article entity in a single query.
	 * By doing so, the transactionId field of each article returned by the query will be populated with the corresponding Transaction entity,
	 * and you can access the properties of the Transaction entity through the transactionId field of the Article entity.
	 * 
	 * The LEFT JOIN FETCH syntax in the query specifies that JPA should load the Shop entity along with the Article entity in a single query.
	 * By doing so, the negozioId field of each article returned by the query will be populated with the corresponding Shop entity,
	 * and you can access the properties of the Shop entity through the negozioId field of the Article entity.
	 * 
	 * @return List of article with LEFT JOIN FETCH with supplierId, transactionId, negozioId
	 */
	@Transactional
	@Query("SELECT a FROM Article a LEFT JOIN FETCH a.negozioId LEFT JOIN FETCH a.supplierId LEFT JOIN FETCH a.transactionId")
    public List<Article> queryGetAllArticleFetchSupplier();

	
	
//	@Transactional
//	@Modifying
//	@Query (value = "UPDATE article SET transazione_id =:firstParam, venduto =:secondParam WHERE id_articolo =:thirdParam", nativeQuery = true)
//	public void queryUpdateSellOutArticle (@Param("firstParam") Long transazione_id, @Param("secondParam") int venduto, @Param("thirdParam") Long id_articolo);
	

}
