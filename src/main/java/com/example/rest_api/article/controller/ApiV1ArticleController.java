package com.example.rest_api.article.controller;

import com.example.rest_api.article.dto.ArticleDTO;
import com.example.rest_api.article.entity.Article;
import com.example.rest_api.article.request.ArticleCreateRequest;
import com.example.rest_api.article.request.ArticleCreateResponse;
import com.example.rest_api.article.request.ArticleModifyRequest;
import com.example.rest_api.article.request.ArticleModifyResponse;
import com.example.rest_api.article.response.ArticleResponse;
import com.example.rest_api.article.response.ArticlesResponse;
import com.example.rest_api.article.service.ArticleService;
import com.example.rest_api.global.jpa.RsData.RsData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController // JSON 형태로 응답을 반환함
@RequiredArgsConstructor
@RequestMapping("/api/v1/articles")
public class ApiV1ArticleController {
    private final ArticleService articleService;

    @GetMapping("") //다건조회
    public RsData<ArticlesResponse> list() {
        List<ArticleDTO> articleList = this.articleService.getList();

        return RsData.of("200", "게시글 다건 조회 성공", new ArticlesResponse(articleList));
    }

    @GetMapping("/{id}") // 단건조회
    public RsData<ArticleResponse> getArticle(@PathVariable("id") Long id) {
        Article  article = this.articleService.getArticle(id);
        ArticleDTO articleDTO = new ArticleDTO(article);

        return RsData.of("200", "게시글 단건 조회 성공", new ArticleResponse(articleDTO));
    }


    @PostMapping("") // 등록
    public RsData<ArticleCreateResponse> create (@Valid @RequestBody ArticleCreateRequest articleCreateRequest){
         Article article = this.articleService.write(articleCreateRequest.getSubject(),articleCreateRequest.getContent());

         return RsData.of("200","등록성공", new ArticleCreateResponse(article));
    }

    @PatchMapping("/{id}") // 수정
    public RsData<ArticleModifyResponse> modify(@PathVariable("id") Long id, @Valid @RequestBody ArticleModifyRequest articleModifyRequest) {
        Article article = this.articleService.getArticle(id);

        if (article == null) return RsData.of(
                "500",
                "%d 번 게시물은 존재하지 않습니다.".formatted(id),
                null
        );
        article = this.articleService.update(article, articleModifyRequest.getSubject(), articleModifyRequest.getContent());
        return RsData.of("200", "수정성공", new ArticleModifyResponse(article));
    }

    @DeleteMapping("/{id}")  // 삭제
    public String delete(@PathVariable("id") Long id){
        System.out.println(id);

        return "삭제";
    }
}
