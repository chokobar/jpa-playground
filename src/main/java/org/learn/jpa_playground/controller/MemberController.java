package org.learn.jpa_playground.controller;


import org.learn.jpa_playground.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/signUp")
    public String signUp(Model model) {
        //model.addAttribute("data", "hello!!");
        return "signUp";
    }
}
