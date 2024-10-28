package com.example.rest_api.article.controller;

import com.example.rest_api.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.GetExchange;

@RestController // 문자를 데이터 형태로 넘겨줌
@RequiredArgsConstructor
@RequestMapping("/api/v1/articles")
public class ApiV1ArticleController {
    private final ArticleService articleService;

    @GetMapping("") // 다건조회
    public String list(){
        return "ㅎ2";
    }

    @GetExchange("/{id}") //단건조회
    public String getArticle(){
        return "";
    }

    @PostMapping("") // 생성
    public String create(){
        return "";
    }

    @PatchMapping("") // 수정
    public String modify(){
        return "";
    }

    @DeleteMapping("/{id}")  // 샂게
    public String delete(){
        return "";
    }
}
