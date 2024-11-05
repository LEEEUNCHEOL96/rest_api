package com.example.rest_api.domain.member.controller;

import com.example.rest_api.domain.member.dto.reponse.MemberResponse;
import com.example.rest_api.domain.member.entity.Member;
import com.example.rest_api.domain.member.dto.request.MemberRequest;
import com.example.rest_api.domain.member.service.MemberService;
import com.example.rest_api.global.RsData.RsData;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/members")
@RequiredArgsConstructor
@Tag(name = "ApiV1MemberController", description = "회원 인증인가 API")
public class ApiV1MemberController {
    private final MemberService memberService;

    @PostMapping("/join")
    public RsData<MemberResponse> join (@Valid @RequestBody MemberRequest memberRequest) {
        Member member = this.memberService.join(memberRequest.getUsername(), memberRequest.getPassword());
        return RsData.of("200", "회원가입이 완료되었습니다.", new MemberResponse(member));
    }

    @PostMapping("/login")
    public ResponseEntity login (@Valid @RequestBody MemberRequest memberRequest, HttpSession httpSession) {
        Member member = this.memberService.getMember(memberRequest.getUsername());
        if (member == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("존재하지 않는 회원입니다.");
        }
        httpSession.setAttribute("USER_ID", member.getUsername());
        return ResponseEntity.ok("로그인 성공");
    }

    @GetMapping("/logout")
    public ResponseEntity logout(HttpSession httpSession) {
        httpSession.invalidate();
        return ResponseEntity.ok("로그아웃 성공");
    }

}