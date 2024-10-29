package com.example.rest_api.article.response;

import com.example.rest_api.article.dto.ArticleDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleResponse {
    private  final ArticleDTO article;
}