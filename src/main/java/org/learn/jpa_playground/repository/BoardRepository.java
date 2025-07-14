package org.learn.jpa_playground.repository;

import org.learn.jpa_playground.domain.BoardDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository <BoardDomain, Integer> {

    @Modifying
    @Query("UPDATE BoardDomain b SET b.viewCount = b.viewCount + 1 WHERE b.id = :id")
    int updateView(@Param("id") Long id);
}
