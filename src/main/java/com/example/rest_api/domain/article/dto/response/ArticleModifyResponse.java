package com.example.rest_api.domain.article.dto.response;

import com.example.rest_api.domain.article.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleModifyResponse {
    private final Article article;

}
