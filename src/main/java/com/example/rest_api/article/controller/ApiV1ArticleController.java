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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController // JSON 형태로 응답을 반환함
@RequestMapping(value = "/api/v1/articles", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "ApiV1ArticleController", description = "게시글 CRUD API")
public class ApiV1ArticleController {
    private final ArticleService articleService;

    @GetMapping("") //다건조회
    @Operation(summary = "게시글 다건 조회") // SWAGGER API 문서
    public RsData<ArticlesResponse> list() {
        List<ArticleDTO> articleList = this.articleService.getList();

        return RsData.of("200", "게시글 다건 조회 성공", new ArticlesResponse(articleList));
    }

    @GetMapping("/{id}") // 단건조회
    @Operation(summary = "게시글 단건 조회") // SWAGGER API 문서
    public RsData<ArticleResponse> getArticle(@PathVariable("id") Long id) {
        Article  article = this.articleService.getArticle(id);
        ArticleDTO articleDTO = new ArticleDTO(article);

        return RsData.of("200", "게시글 단건 조회 성공", new ArticleResponse(articleDTO));
    }


    @PostMapping("") // 등록
    @Operation(summary = "게시글 등록") // SWAGGER API 문서
    public RsData<ArticleCreateResponse> create (@Valid @RequestBody ArticleCreateRequest articleCreateRequest){
         Article article = this.articleService.write(articleCreateRequest.getSubject(),articleCreateRequest.getContent());

         return RsData.of("200","등록성공", new ArticleCreateResponse(article));
    }

    @PatchMapping("/{id}") // 수정
    @Operation(summary = "게시글 수정") // SWAGGER API 문서
    public RsData<ArticleModifyResponse> modify(@PathVariable("id") Long id, @Valid @RequestBody ArticleModifyRequest articleModifyRequest) {
        Article article = this.articleService.getArticle(id);

        if (article == null)
            return RsData.of(
                "500",
                "%d 번 게시물은 존재하지 않습니다.".formatted(id),
                null
            );

        article = this.articleService.update(article, articleModifyRequest.getSubject(), articleModifyRequest.getContent());
        return RsData.of("200", "수정성공", new ArticleModifyResponse(article));
    }

    @DeleteMapping("/{id}")  // 삭제
    @Operation(summary = "게시글 삭제") // SWAGGER API 문서
    public RsData<ArticleResponse> delete(@PathVariable("id") Long id){
        Article article = this.articleService.getArticle(id);

        if (article == null)
            return RsData.of(
                "500",
                "%d 번 게시물은 존재하지 않습니다.".formatted(id),
                null
        );

        this.articleService.delete(article);
        ArticleDTO articleDTO = new ArticleDTO(article);

        return RsData.of("200","삭제성공", new ArticleResponse(articleDTO));
    }
}
