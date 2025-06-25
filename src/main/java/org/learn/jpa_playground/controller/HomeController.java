package org.learn.jpa_playground.controller;

import lombok.extern.slf4j.Slf4j;
import org.learn.jpa_playground.domain.MemberDomain;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@SessionAttribute(value = "member", required = false) MemberDomain member,
                       Model model) {
        model.addAttribute("member", member);
        return "index";
    }

}
