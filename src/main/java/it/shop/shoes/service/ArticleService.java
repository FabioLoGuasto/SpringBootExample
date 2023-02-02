package it.shop.shoes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.shop.shoes.dto.ArticleDto;
import it.shop.shoes.dto.ArticleDto2;
import it.shop.shoes.model.Article;

@Service
public interface ArticleService {

	
	public Article insert(Article a); 
	public List<Article> getArticles();
	public void update(Long id,Article a);
	public void updateFromCode(Long id, Article a);
	public void delete (Long id);
	
	public ArticleDto EntityToDto(Article art);
	public Article dtoToEntity(ArticleDto dto);
	public List<ArticleDto> getAllArticleDto(List<Article>art);
	public List<ArticleDto2> getAllArticleDto2(List<Article>art);
	public ArticleDto2 EntityToDto2(Article art);
}
