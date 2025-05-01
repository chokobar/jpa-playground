package org.learn.jpa_playground.controller;


import lombok.extern.slf4j.Slf4j;
import org.learn.jpa_playground.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

    //private final Logger log = LoggerFactory.getLogger(getClass()); @Slf4j 이걸로 대체

    @Autowired
    private MemberService memberService;

    @GetMapping("/signUp")
    public String signUp(Model model) {
        //model.addAttribute("data", "hello!!");
        return "signUp";
    }
}
