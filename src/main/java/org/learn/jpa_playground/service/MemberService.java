package org.learn.jpa_playground.service;

import jakarta.transaction.Transactional;
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

    @Transactional
    public MemberDomain save(MemberDTO memberDto) {
        MemberDomain memberDomain = MemberDomain.builder()
                .uniqueKey(memberDto.getUniqueKey())
                .userId(memberDto.getUserId())
                .userPassword(memberDto.getUserPassword())
                .userName(memberDto.getUserName())
                .userEmail(memberDto.getUserEmail())
                .userPhone(memberDto.getUserPhone())
                .createdDate(new Date())
                .build();

        return memberRepository.save(memberDomain);
    }

}
