package it.shop.shoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.shop.shoes.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long>{

}
