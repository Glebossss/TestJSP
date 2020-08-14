package com.example.demo.controller;


import com.example.demo.service.AllUserService;
import com.example.demo.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class AdminController {

    private static final String ADMINROLE = "ROLE_ADMIN";
    private static final String STUDENTROLE = "ROLE_STUDENT";

    @Autowired
    AllUserService allUserService;

    @Autowired
    SubjectService subjectService;


    @RequestMapping("/admininput")
    public String admininput(OAuth2AuthenticationToken auth) {

        Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        String email = (String) attrs.get("email");

        String roleUser = allUserService.findByLogin(email).getRole().toString();

        if (roleUser.equals(ADMINROLE))
            return "/admin";

        else if (roleUser.equals(STUDENTROLE))
            return "/student";

        else
            return "/teacher";
    }
}
