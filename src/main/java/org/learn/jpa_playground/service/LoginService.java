package org.learn.jpa_playground.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.learn.jpa_playground.domain.MemberDomain;
import org.learn.jpa_playground.repository.LoginRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final LoginRepository loginRepository;

    @Transactional
    public MemberDomain login(String userId, String userPassword) {
        log.info("userId = {}", userId);
        MemberDomain byUserId = loginRepository.findByUserId(userId);

        if (byUserId == null) {
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        }

        if (!passwordEncoder.matches(userPassword, byUserId.getUserPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        return byUserId;
    }

}
