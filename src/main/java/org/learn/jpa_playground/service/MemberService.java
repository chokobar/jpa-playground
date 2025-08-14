package org.learn.jpa_playground.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.learn.jpa_playground.domain.MemberDomain;
import org.learn.jpa_playground.dto.MemberDTO;
import org.learn.jpa_playground.enums.UserRole;
import org.learn.jpa_playground.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


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
                .userPassword(passwordEncoder.encode(memberDto.getUserPassword()))      //패스워드 암호화
                .userName(memberDto.getUserName())
                .userEmail(memberDto.getUserEmail())
                .userPhone(memberDto.getUserPhone())
                .userRole(UserRole.USER.name())
                .createdDate(new Date())
                .build();

        return memberRepository.save(memberDomain);
    }


    @Transactional
    public List<MemberDTO> findAll() {
        return memberRepository.findAll()
                .stream()
                .map(memberDomain -> MemberDTO.builder()
                        .uniqueKey(memberDomain.getUniqueKey())
                        .userId(memberDomain.getUserId())
                        .userPassword(memberDomain.getUserPassword())
                        .userName(memberDomain.getUserName())
                        .userEmail(memberDomain.getUserEmail())
                        .userPhone(memberDomain.getUserPhone())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional
    public MemberDTO findByUserId(String userId) {

        MemberDomain memberDomain = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원은 존재하지 않습니다. ID : " + userId));

        return MemberDTO.builder()
                .uniqueKey(memberDomain.getUniqueKey())
                .userId(memberDomain.getUserId())
                .userPassword(memberDomain.getUserPassword())
                .userName(memberDomain.getUserName())
                .userEmail(memberDomain.getUserEmail())
                .userPhone(memberDomain.getUserPhone())
                .createdDate(String.valueOf(memberDomain.getCreatedDate()))
                .build();
    }

    public MemberDomain update(MemberDTO memberDto) {
        MemberDomain existing = memberRepository.findByUserId(memberDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("회원 없음"));

        existing.setUserName(memberDto.getUserName());
        existing.setUserEmail(memberDto.getUserEmail());
        existing.setUserPhone(memberDto.getUserPhone());
        existing.setEditdDate(new Date());

        // 비밀번호 입력 시에만 암호화해서 반영
        if (memberDto.getUserPassword() != null && !memberDto.getUserPassword().isBlank()) {
            existing.setUserPassword(passwordEncoder.encode(memberDto.getUserPassword()));
        }

        return memberRepository.save(existing);
    }

    public MemberDomain passwordUpdate(String userId, String newPassword) {
        MemberDomain existing = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("회원 없음"));

        existing.setUserPassword(passwordEncoder.encode(newPassword));
        existing.setEditdDate(new Date());

        return memberRepository.save(existing);
    }

    @Transactional
    public void memberRemove(String userId) {
        memberRepository.deleteByUserId(userId);
    }

}
