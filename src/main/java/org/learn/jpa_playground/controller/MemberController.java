package org.learn.jpa_playground.controller;


import lombok.extern.slf4j.Slf4j;
import org.learn.jpa_playground.domain.MemberDomain;
import org.learn.jpa_playground.dto.MemberDTO;
import org.learn.jpa_playground.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("memberDTO", new MemberDTO());
        return "signUp";
    }

    @PostMapping("/save")
    public String memberSave(@ModelAttribute MemberDTO memberDto) {
        log.info("Member save:{}", memberDto);

        memberService.save(memberDto);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String members(Model model) {
        List<MemberDTO> members = memberService.findAll();
        log.info("Member list:{}", members);
        model.addAttribute("members", members);
        return "members";
    }

    @GetMapping("/members/{userId}")
    public String membersDetail(@PathVariable String userId, Model model) {
        log.info("Member detail:{}", userId);
        //MemberDTO memberDTO =  memberService.findById(userId);
        // JPA에서는 findById는 Integer인데 현재 userId는 String이라서 findById를 사용못하고
        // 새롭게 findByUserId 메서드를 만들어야함
        MemberDTO memberDTO =  memberService.findByUserId(userId);
        model.addAttribute("member", memberDTO);
        return "memberDetails";
    }
}
