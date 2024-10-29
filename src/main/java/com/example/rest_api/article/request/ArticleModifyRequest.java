package com.example.rest_api.article.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ArticleModifyRequest{
    @NotBlank
    private String subject;
    @NotBlank
    private String content;
}