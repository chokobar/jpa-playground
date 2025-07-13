package org.learn.jpa_playground.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.learn.jpa_playground.domain.BoardDomain;
import org.learn.jpa_playground.dto.BoardDTO;
import org.learn.jpa_playground.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public List<BoardDTO> findAll() {
        return boardRepository.findAll()
                .stream()
                .map(boardDomain -> BoardDTO.builder()
                        .id((long) boardDomain.getId())
                        .title(boardDomain.getTitle())
                        .content(boardDomain.getContent())
                        .writer(boardDomain.getWriter())
                        .viewCount(boardDomain.getViewCount())
                        .createdDate(String.valueOf(boardDomain.getCreatedDate()))
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional
    public BoardDTO findById(Integer id) {
        BoardDomain boardDomain = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. ID:" + id));

        return BoardDTO.builder()
                .id((long) boardDomain.getId())
                .title(boardDomain.getTitle())
                .content(boardDomain.getContent())
                .writer(boardDomain.getWriter())
                .createdDate(String.valueOf(boardDomain.getCreatedDate()))
                .updatedDate(String.valueOf(boardDomain.getUpdated_date()))
                .viewCount(boardDomain.getViewCount())
                .build();
    }

}
