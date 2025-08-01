package org.learn.jpa_playground.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.learn.jpa_playground.domain.BoardDomain;
import org.learn.jpa_playground.dto.BoardDTO;
import org.learn.jpa_playground.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
                        .createdDate(LocalDateTime.now())
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
                .createdDate(LocalDateTime.now())
                .updatedDate(String.valueOf(boardDomain.getUpdatedDate()))
                .viewCount(boardDomain.getViewCount())
                .build();
    }

    @Transactional
    public int updateView(Integer id){
        return boardRepository.updateView(Long.valueOf(id));
    }

    public BoardDomain update(BoardDTO boardDTO) {
        // DTO를 Entity 변환
        BoardDomain board = boardRepository.findById(boardDTO.getId().intValue())
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());
        board.setUpdatedDate(new Date());

        return boardRepository.save(board);
    }

    @Transactional
    public void delete(Integer id) {
        boardRepository.deleteById(id);
    }

}
