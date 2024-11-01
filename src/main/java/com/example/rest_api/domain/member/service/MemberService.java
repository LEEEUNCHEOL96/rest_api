package com.example.rest_api.domain.member.service;

import com.example.rest_api.domain.member.entity.Member;
import com.example.rest_api.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void join (String username, String password){
        Member member = Member.builder()
                .username(username)
                .password(passwordEncoder.encode(password)) // 시큐리티 도입
                .build();

        this.memberRepository.save(member);
    }
}