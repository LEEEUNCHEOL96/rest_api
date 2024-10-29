package com.example.rest_api.article.controller;

import com.example.rest_api.article.dto.ArticleDTO;
import com.example.rest_api.article.entity.Article;
import com.example.rest_api.article.request.ArticleCreateRequest;
import com.example.rest_api.article.request.ArticleModifyRequest;
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
        List<ArticleDTO> articleList = new ArrayList<>();

        articleList.add(new ArticleDTO(new Article("제목1","내용1")));
        articleList.add(new ArticleDTO(new Article("제목2","내용2")));
        articleList.add(new ArticleDTO(new Article("제목3","내용3")));

        return RsData.of("200", "게시글 다건 조회 성공", new ArticlesResponse(articleList));
    }



    @GetMapping("/{id}") // 단건조회
    public RsData<ArticleResponse> getArticle(@PathVariable("id") Long id) {

        // articleDTOList.add(new ArticleDTO(new Article("단건","조회")));
        Article article = new Article("단건","조회");
        ArticleDTO articleDTO = new ArticleDTO(article);

        return RsData.of("200", "게시글 단건 조회 성공", new ArticleResponse(articleDTO));
    }


    @PostMapping("") // 등록
    public String create(@Valid @RequestBody ArticleCreateRequest articleCreateRequest){
        System.out.println(articleCreateRequest.getSubject());
        System.out.println(articleCreateRequest.getContent());

        return "동록완료";
    }

    @PatchMapping("/{id}") // 수정
    public String modify(@PathVariable("id") Long id,@Valid @RequestBody ArticleModifyRequest articleModifyRequest){
        System.out.println(id);
        System.out.println(articleModifyRequest.getSubject());
        System.out.println(articleModifyRequest.getContent());

        return "수정완료";
    }

    @DeleteMapping("/{id}")  // 삭제
    public String delete(@PathVariable("id") Long id){
        System.out.println(id);

        return "삭제";
    }
}
