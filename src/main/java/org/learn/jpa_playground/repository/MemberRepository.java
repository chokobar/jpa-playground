package org.learn.jpa_playground.repository;

import org.learn.jpa_playground.domain.MemberDomain;
import org.learn.jpa_playground.dto.MemberDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberDomain, Integer> {

    Optional<MemberDomain> findByUserId(String userId);

    void deleteByUserId(String userId);
}
