package com.example.rest_api.article.controller;

import com.example.rest_api.article.dto.ArticleDTO;
import com.example.rest_api.article.entity.Article;
import com.example.rest_api.article.service.ArticleService;
import com.example.rest_api.giobal.jpa.RsData.RsData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
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

    @AllArgsConstructor
    @Getter
    public static class ArticlesResponse {
        private final List<ArticleDTO> articleList;
    }
    @GetMapping("") //다건조회
    public RsData<ArticlesResponse> list() {
        List<ArticleDTO> articleList = new ArrayList<>();

        articleList.add(new ArticleDTO(new Article("제목1","내용1")));
        articleList.add(new ArticleDTO(new Article("제목2","내용2")));
        articleList.add(new ArticleDTO(new Article("제목3","내용3")));

        return RsData.of("200", "게시글 다건 조회 성공", new ArticlesResponse(articleList));
    }

    @Getter
    @AllArgsConstructor
    public static class ArticleResponse {
        private  final ArticleDTO article;
    }


    @GetMapping("/{id}") // 단건조회
    public RsData<ArticleResponse> getArticle(@PathVariable("id") Long id) {

        // articleDTOList.add(new ArticleDTO(new Article("단건","조회")));
        Article article = new Article("단건","조회");
        ArticleDTO articleDTO = new ArticleDTO(article);

        return RsData.of("200", "게시글 단건 조회 성공", new ArticleResponse(articleDTO));
    }

    @Data // gettet settet 같은 lombok 에서 기본을 다 제공하는 어노테이션
    public static class ArticleRequest{
        @NotBlank
        private String subject;
        @NotBlank
        private String content;
    }

    @PostMapping("") // 등록
    public String create(@Valid @RequestBody ArticleRequest articleRequest){
        System.out.println(articleRequest.getSubject());
        System.out.println(articleRequest.getContent());

        return "동록완료";
    }

    @PatchMapping("/{id}") // 수정
    public String modify(@PathVariable("id") Long id,@Valid @RequestBody ArticleRequest articleRequest){
        System.out.println(id);
        System.out.println(articleRequest.getSubject());
        System.out.println(articleRequest.getContent());

        return "수정완료";
    }

    @DeleteMapping("/{id}")  // 샂게
    public String delete(@PathVariable("id") Long id){
        System.out.println(id);

        return "삭제";
    }
}
