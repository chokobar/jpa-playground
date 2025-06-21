package org.learn.jpa_playground.controller;

import lombok.extern.slf4j.Slf4j;
import org.learn.jpa_playground.domain.MemberDomain;
import org.learn.jpa_playground.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class LoginController {

    @Autowired
    private  LoginService loginService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("login")
    public String login(@RequestParam("userId") String userId,
                        @RequestParam("userPassword") String userPassword, Model model) {
        log.info("userid : {}, userPassword : {}", userId, userPassword);
        try {
            MemberDomain member = loginService.login(userId, userPassword);
            model.addAttribute("member", member);
            return "Welcome to JPA Playground";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }
}