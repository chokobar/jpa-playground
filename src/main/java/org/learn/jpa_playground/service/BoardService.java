package org.learn.jpa_playground.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.learn.jpa_playground.domain.BoardDomain;
import org.learn.jpa_playground.dto.BoardDTO;
import org.learn.jpa_playground.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public BoardDomain save(BoardDTO boardDTO) {
        BoardDomain boardDomain = BoardDomain.builder()
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .writer(boardDTO.getWriter())
                .createdDate(new Date())
                .viewCount(0)
                .build();

        return boardRepository.save(boardDomain);
    }
}
