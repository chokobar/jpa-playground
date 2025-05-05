package org.learn.jpa_playground.service;

import lombok.RequiredArgsConstructor;
import org.learn.jpa_playground.domain.MemberDomain;
import org.learn.jpa_playground.dto.MemberDTO;
import org.learn.jpa_playground.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    public MemberDomain save(MemberDTO memberDto) {
        MemberDomain memberDomain = MemberDomain.builder()
                .id(memberDto.getId())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .name(memberDto.getName())
                .age(memberDto.getAge())
                .email(memberDto.getEmail())
                .phone(memberDto.getPhone())
                .createdDate(new Date())
                .build();

        return memberRepository.save(memberDomain);
    }

}
