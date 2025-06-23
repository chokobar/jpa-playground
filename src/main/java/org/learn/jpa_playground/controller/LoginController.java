package org.learn.jpa_playground.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.learn.jpa_playground.domain.MemberDomain;
import org.learn.jpa_playground.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private  LoginService loginService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login-process")
    public String login(@RequestParam("userId") String userId,
                        @RequestParam("userPassword") String userPassword,
                        HttpSession session,
                        Model model) {
        log.info("login start");
        log.info("userid : {}, userPassword : {}", userId, userPassword);
        try {
            MemberDomain member = loginService.login(userId, userPassword);
            //model.addAttribute("member", member);
            session.setAttribute("member", member);

            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(userId, null, null);
            SecurityContextHolder.getContext().setAuthentication(auth);

            return "redirect:/";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        log.info("logout start");
        log.info("session id : {}", session.getId());
        //session.removeAttribute("member");  //로그인 정보만 비워두고 세션은 유지
        session.invalidate();   // 현재 세션 자체를 완전히 파기(보안상 안전)
        return "redirect:/";
    }
}