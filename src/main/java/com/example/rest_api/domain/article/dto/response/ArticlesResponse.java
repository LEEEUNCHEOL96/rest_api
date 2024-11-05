package com.example.rest_api.domain.article.dto.response;

import com.example.rest_api.domain.article.dto.ArticleDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ArticlesResponse {
    private final List<ArticleDTO> articleList;
}