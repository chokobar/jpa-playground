package org.learn.jpa_playground.controller;


import lombok.extern.slf4j.Slf4j;
import org.learn.jpa_playground.dto.MemberDTO;
import org.learn.jpa_playground.service.MemberService;
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

    /**
     * 회원 가입
     * @param model
     * @return
     */
    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("memberDTO", new MemberDTO());
        return "signUp";
    }

    /**
     * 회워가입 로직
     * @param memberDto
     * @return
     */
    @PostMapping("/save")
    public String memberSave(@ModelAttribute MemberDTO memberDto) {
        log.info("Member save:{}", memberDto);

        memberService.save(memberDto);
        return "redirect:/";
    }

    /**
     * 회원 리스트
     * @param model
     * @return
     */
    @GetMapping("/members")
    public String members(Model model) {
        List<MemberDTO> members = memberService.findAll();
        log.info("Member list:{}", members);
        model.addAttribute("members", members);
        return "members";
    }

    /**
     * 회원 상세 페이지
     * @param userId
     * @param model
     * @return
     */
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

    /**
     * 사용자 수정페이지
     * @param userId
     * @param model
     * @return
     */
    @GetMapping("/members/{userId}/edit")
    public String membersEdit(@PathVariable String userId, Model model) {
        log.info("Member edit:{}", userId);

        MemberDTO memberDTO =  memberService.findByUserId(userId);
        log.info("Member edit2:{}", memberDTO);
        model.addAttribute("member", memberDTO);
        return "memberEdit";
    }

    @PostMapping("/members/{userId}/edit")
    public String edit(@PathVariable String userId, @ModelAttribute MemberDTO member) {
        log.info("Member edit2:{}", userId);
        memberService.update(member);
        return "redirect:/";
    }

    @GetMapping("/members/{userId}/passwordEdit")
    public String passwordEdit(@PathVariable String userId) {
        log.info("Member passwordEdit:{}", userId);
        String initPassword = "12345";
        memberService.passwordUpdate(userId, initPassword);
        return "redirect:/";
    }

    @GetMapping("/members/{userId}/remove")
    public String memberRemove(@PathVariable("userId") String userId) {
        log.info("Member remove:{}", userId);
        memberService.memberRemove(userId);
        return "redirect:/";
    }
}
