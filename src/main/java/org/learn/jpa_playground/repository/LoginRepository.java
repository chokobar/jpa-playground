package org.learn.jpa_playground.repository;

import org.learn.jpa_playground.domain.MemberDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<MemberDomain, Integer> {

    MemberDomain findByUserId(String userId);

}
