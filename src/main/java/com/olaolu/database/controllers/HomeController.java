package com.olaolu.database.controllers;

/**
 * @author akano.olanrewaju  @on 13/10/2019
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }
    @GetMapping("/admin")
    public String adminIndex() {
        return "admin/adminindex";
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView, HttpServletRequest httpServletRequest) {
        modelAndView.setViewName("login");
        return modelAndView;
    }
    @GetMapping("/logout")
    public String logOut() {
        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }

}