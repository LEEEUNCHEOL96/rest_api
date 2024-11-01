package com.example.rest_api.domain.article.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data // gettet settet 같은 lombok 에서 기본을 다 제공하는 어노테이션
public class ArticleCreateRequest{
    @NotBlank
    private String subject;
    @NotBlank
    private String content;
}