package com.example.rest_api.article.repository;

import com.example.rest_api.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article ,Long> {
}
