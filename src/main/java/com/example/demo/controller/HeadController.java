package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HeadController {

    private static final String LOGIN = "login";
    private static final String ABOUT = "about";
    private static final String LOGOUT = "redirect:/login";


    @RequestMapping("/login")
    public String loginPage() {
        return LOGIN;
    }

    @RequestMapping("/about")
    public String about() {
        return ABOUT;
    }


    @RequestMapping("/logout")
    public String logout() {
        return LOGOUT;
    }

}
