package org.learn.jpa_playground.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.learn.jpa_playground.config.ValidatorConfig;
import org.learn.jpa_playground.dto.MemberDTO;
import org.learn.jpa_playground.form.MemberSaveForm;
import org.learn.jpa_playground.form.MemberUpdateForm;
import org.learn.jpa_playground.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final ValidatorConfig validatorConfig;
    private final MemberService memberService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(validatorConfig);
    }


    /**
     * 회원 가입
     * @param model
     * @return
     */
    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("memberDTO", new MemberDTO());
        return "member/signUp";
    }

    /**
     * 회워가입 로직
     * @param memberDto
     * @return
     */
    @PostMapping("/save")
    public String memberSave(@Validated @ModelAttribute("memberDTO") MemberSaveForm memberDto, BindingResult bindingResult) {
        log.info("Member save:{}", memberDto);

        if (bindingResult.hasErrors()) {
            log.warn("Validation errors: {}", bindingResult.getAllErrors());
            return "member/signUp";
        }

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUniqueKey(memberDto.getUniqueKey());
        memberDTO.setUserId(memberDto.getUserId());
        memberDTO.setUserPassword(memberDto.getUserPassword());
        memberDTO.setUserName(memberDto.getUserName());
        memberDTO.setUserEmail(memberDto.getUserEmail());
        memberDTO.setUserPhone(memberDto.getUserPhone());

        memberService.save(memberDTO);
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
        return "member/members";
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
        return "member/memberDetails";
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
        return "member/memberEdit";
    }

    @PostMapping("/members/{userId}/edit")
    public String edit(@PathVariable String userId, @Validated @ModelAttribute("member") MemberUpdateForm memberDto,
                       BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            log.warn("Validation errors: {}", bindingResult.getAllErrors());
            model.addAttribute("member", memberDto);
            return "member/memberEdit";
        }

        MemberDTO memberDTO = new MemberDTO();

        memberDTO.setUserId(userId);
        memberDTO.setUserName(memberDto.getUserName());
        memberDTO.setUserEmail(memberDto.getUserEmail());
        memberDTO.setUserPhone(memberDto.getUserPhone());

        log.info("Member edit2:{}", userId);
        memberService.update(memberDTO);
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
