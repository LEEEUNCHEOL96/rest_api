package com.example.rest_api.domain.article.dto;

import com.example.rest_api.domain.article.entity.Article;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleDTO {
    private final Long id;

    private final String subject;

    private final String content;

    private final LocalDateTime createdDate;

    private final LocalDateTime modifiedDate;

    private final String author;

    public ArticleDTO(Article article){
        this.id= article.getId();
        this.subject=article.getSubject();
        this.content=article.getContent();
        this.createdDate=article.getCreatedDate();
        this.modifiedDate=article.getModifiedDate();
        this.author=article.getMember().getUsername();
    }
}