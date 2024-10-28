package com.example.rest_api.article.controller;

import com.example.rest_api.article.dto.ArticleDTO;
import com.example.rest_api.article.entity.Article;
import com.example.rest_api.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.GetExchange;

import java.util.ArrayList;
import java.util.List;

@RestController // 문자를 데이터 형태로 넘겨줌
@RequiredArgsConstructor
@RequestMapping("/api/v1/articles")
public class ApiV1ArticleController {
    private final ArticleService articleService;

    @GetMapping("") // 다건조회
    public List<ArticleDTO> list(){
        List<ArticleDTO> articleDTOList = new ArrayList<>();

        articleDTOList.add(new ArticleDTO(new Article("제목1","내용1")));
        articleDTOList.add(new ArticleDTO(new Article("제목2","내용2")));
        articleDTOList.add(new ArticleDTO(new Article("제목3","내용3")));

        return articleDTOList;
    }

    @GetMapping("/{id}") //단건조회
    public ArticleDTO getArticle(@PathVariable("id") Long id){

        // articleDTOList.add(new ArticleDTO(new Article("단건","조회")));
        Article article = new Article("단건","조회");
        ArticleDTO articleDTO = new ArticleDTO(article);

        return articleDTO;
    }

    @PostMapping("") // 생성
    public String create(){
        return "등록";
    }

    @PatchMapping("/{id}") // 수정
    public String modify(@PathVariable("id") Long id){
        return "수정";
    }

    @DeleteMapping("/{id}")  // 샂게
    public String delete(@PathVariable("id") Long id){
        return "삭제";
    }
}
