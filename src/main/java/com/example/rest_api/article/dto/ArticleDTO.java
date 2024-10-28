package com.example.rest_api.article.dto;

import com.example.rest_api.article.entity.Article;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
public class ArticleDTO {
    private final Long id;

    private final String subject;

    private final String content;

    private final LocalDateTime createdDate;

    private final LocalDateTime modifiedDate;

    public ArticleDTO(Article article){
        this.id= article.getId();
        this.subject=article.getSubject();
        this.content=article.getContent();
        this.createdDate=article.getCreatedDate();
        this.modifiedDate=article.getModifiedDate();
    }
}
