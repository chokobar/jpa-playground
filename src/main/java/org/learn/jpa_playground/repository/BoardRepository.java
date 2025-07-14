package org.learn.jpa_playground.repository;

import org.learn.jpa_playground.domain.BoardDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository <BoardDomain, Integer> {

    // JPQL에서는 테이블명(board)과 컬럼명(ex:view_count 사용불가)이 아닌,
    // 엔티티 클래스명(BoardDomain)과 필드명(ex:viewCount 사용가능)을 사용해야 한다.
    @Modifying
    @Query("UPDATE BoardDomain b SET b.viewCount = b.viewCount + 1 WHERE b.id = :id")
    int updateView(@Param("id") Long id);
}
