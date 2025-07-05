package org.learn.jpa_playground.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.learn.jpa_playground.domain.MemberDomain;
import org.learn.jpa_playground.dto.BoardDTO;
import org.learn.jpa_playground.service.BoardService;
import org.learn.jpa_playground.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

    @GetMapping("/boardForm")
    public String boardForm(Model model, @SessionAttribute("member") MemberDomain member) {
        log.info("boardForm 진입");
        BoardDTO dto = new BoardDTO();
        dto.setWriter(member.getUserName());
        model.addAttribute("BoardDTO", dto);
        return "boardForm";
    }

    @PostMapping("/save")
    public String save(@Validated @ModelAttribute("BoardDTO") BoardDTO boardDTO, BindingResult bindingResult) {
        log.info("Board Save:{}", boardDTO);

        if (bindingResult.hasErrors()) {
            log.warn("Validation errors: {}", bindingResult.getAllErrors());
            return "boardForm";
        }

        BoardDTO boardData = new BoardDTO();
        boardData.setId(boardDTO.getId());
        boardData.setTitle(boardDTO.getTitle());
        boardData.setContent(boardDTO.getContent());

        boardService.save(boardDTO);
        return "redirect:/";
    }
}
