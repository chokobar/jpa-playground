package org.learn.jpa_playground.repository;

import org.learn.jpa_playground.domain.MemberDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberDomain, Integer> {
}
