package org.learn.jpa_playground.repository;

import org.learn.jpa_playground.domain.BoardDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository <BoardDomain, Integer> {
}
