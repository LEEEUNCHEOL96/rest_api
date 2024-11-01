package com.example.rest_api.domain.article.response;

import com.example.rest_api.domain.article.dto.ArticleDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleResponse {
    private  final ArticleDTO article;
}